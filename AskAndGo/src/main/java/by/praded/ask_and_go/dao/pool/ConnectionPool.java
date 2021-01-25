package by.praded.ask_and_go.dao.pool;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Kiryl Praded
 * 04.12.2020
 * <p>
 * Implimentation of pool of connections.
 */
public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static ConnectionPool instance;
    private String url;
    private String login;
    private String password;
    private Lock lock;
    private BlockingQueue<WrappedConnection> freeConnections;
    private Set<WrappedConnection> takenConnections;
    private int poolSize;

    private ConnectionPool() {
        lock = new ReentrantLock();
    }


    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public void init(String driverClass, String url, String user,
                     String password, int poolSize) throws ConnectionPoolException {
        try {
            Class.forName(driverClass);
            this.url = url;
            this.login = user;
            this.password = password;
            this.poolSize = poolSize;
            freeConnections = new LinkedBlockingQueue<>();
            takenConnections = new HashSet<>();
        } catch (ClassNotFoundException e) {
            logger.fatal("Connection pool initialization failed.", e);
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }


    public Connection takeConnection() throws ConnectionPoolException {
        lock.lock();
        WrappedConnection connection;
        try {
            //todo уточнить возможна ли тут рассинхронизация
            if(!freeConnections.isEmpty()) {
                connection = freeConnections.take();
            } else if(takenConnections.size() < poolSize) {
                connection = new WrappedConnection(DriverManager.getConnection(url, login, password));
            } else {
                connection = freeConnections.take();
            }
            takenConnections.add(connection);
            lock.unlock();
        } catch (InterruptedException | SQLException e) {
            logger.error("Failed to take connection from queue.", e);
            lock.unlock();
            Thread.currentThread().interrupt();
            /*
            InterruptedExceptions should either be rethrown - immediately or after cleaning up the method's state
             - or the thread should be re-interrupted by calling Thread.interrupt() even if this is supposed to be a single-threaded application.
              Any other course of action risks delaying thread shutdown and loses the information that the thread was interrupted - probably without finishing its task.
             */
            throw new ConnectionPoolException(e.getMessage(), e);
        }
        logger.info("Connection taken");
        return connection;
    }

    public void destroyPool() throws ConnectionPoolException { // уничтожение пула
        clearConnectionQueue();
        logger.info("Pool destroyed.");
    }

    private void clearConnectionQueue() throws ConnectionPoolException {
        try {
            closeTakenConnections(takenConnections);
            closeFreeConnections(freeConnections);
        } catch (SQLException e) {
            logger.error("Error closing the connection.", e);
            throw new ConnectionPoolException("Failed to close connection");
        }
    }

    private void closeTakenConnections(Set<WrappedConnection> takenConnections) throws SQLException {
        for (WrappedConnection connection : takenConnections) { // для кажого элемента в сете
            if (!connection.getAutoCommit()) {
                connection.commit(); // коммитим всё
            }
            connection.completelyClose(); // и окончательно закрываем конекшен
        }
    }

    private void closeFreeConnections(BlockingQueue<WrappedConnection> queue) throws SQLException {
        WrappedConnection connection;

        while ((connection = queue.poll()) != null) { // пока очередь не опустела
            if (!connection.getAutoCommit()) {
                connection.commit(); // коммитим всё
            }
            connection.completelyClose(); // и по-настоящему закрываем конекшен
        }
    }

    void freeConnection(WrappedConnection connection) {
        try {
            connection.clearWarnings();
            connection.setAutoCommit(true);
            takenConnections.remove(connection);
            freeConnections.offer(connection);
            logger.info("Connection returned.");
        } catch (SQLException e) {
            logger.error("Failed to return connection.", e);
        }


    }


}
