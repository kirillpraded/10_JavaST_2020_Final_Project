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
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    /**
     * Instance of connection pool for singleton.
     */
    private static ConnectionPool instance;
    /**
     * Lock for locking from threads, when one thread is in.
     */
    private final Lock LOCK;
    /**
     * URL to connect to database.
     */
    private String url;
    /**
     * Login for connection.
     */
    private String login;
    /**
     * Password for connection
     */
    private String password;
    /**
     * Collection of free connections.
     */
    private BlockingQueue<WrappedConnection> freeConnections;
    /**
     * Collection of taken connections.
     */
    private Set<WrappedConnection> takenConnections;
    /**
     * Size of connection pool to limit it.
     */
    private int poolSize;

    /**
     * Class constructor.
     */
    private ConnectionPool() {
        LOCK = new ReentrantLock();
    }

    /**
     * Static method to get instance of class.
     *
     * @return instance of the class.
     */
    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * Method to initialize pool.
     *
     * @param driverClass - full name of the driver class.
     * @param url         - value for {@link ConnectionPool#url}.
     * @param user        - value for {@link ConnectionPool#login}.
     * @param password    - value for {@link ConnectionPool#password}.
     * @param poolSize    - value for {@link ConnectionPool#poolSize}.
     * @throws ConnectionPoolException - may be thrown when class of driver is not found.
     */
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


    /**
     * Method to take connection.
     *
     * @return taken connection.
     * @throws ConnectionPoolException - may be thrown if there is an error communicating with the pool.
     */
    public Connection takeConnection() throws ConnectionPoolException {
        LOCK.lock();
        WrappedConnection connection;
        try {
            if (!freeConnections.isEmpty()) {
                connection = freeConnections.take();
            } else if (takenConnections.size() < poolSize) {
                connection = new WrappedConnection(DriverManager.getConnection(url, login, password));
            } else {
                connection = freeConnections.take();
            }
            takenConnections.add(connection);
            LOCK.unlock();
        } catch (InterruptedException | SQLException e) {
            logger.error("Failed to take connection from queue.", e);
            LOCK.unlock();
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

    /**
     * Method to destroy pool.
     *
     * @throws ConnectionPoolException - may be thrown if there is an error communicating with the pool.
     */
    public void destroyPool() throws ConnectionPoolException { // уничтожение пула
        clearConnectionQueue();
        logger.info("Pool destroyed.");
    }

    /**
     * Method to clear collections with connections.
     *
     * @throws ConnectionPoolException - may be thrown if there is an error communicating with the pool.
     */
    private void clearConnectionQueue() throws ConnectionPoolException {
        try {
            closeTakenConnections(takenConnections);
            closeFreeConnections(freeConnections);
        } catch (SQLException e) {
            logger.error("Error closing the connection.", e);
            throw new ConnectionPoolException("Failed to close connection");
        }
    }

    /**
     * Method to close all taken connections.
     *
     * @param takenConnections - set of taken connections to close.
     * @throws SQLException - exception of the {@link Connection}.
     */
    private void closeTakenConnections(Set<WrappedConnection> takenConnections) throws SQLException {
        for (WrappedConnection connection : takenConnections) { // для кажого элемента в сете
            if (!connection.getAutoCommit()) {
                connection.commit(); // коммитим всё
            }
            connection.completelyClose(); // и окончательно закрываем конекшен
        }
    }

    /**
     * Method to close all free connections.
     *
     * @param queue - queue of taken connections to close.
     * @throws SQLException - exception of the {@link Connection}.
     */
    private void closeFreeConnections(BlockingQueue<WrappedConnection> queue) throws SQLException {
        WrappedConnection connection;

        while ((connection = queue.poll()) != null) { // пока очередь не опустела
            if (!connection.getAutoCommit()) {
                connection.commit(); // коммитим всё
            }
            connection.completelyClose(); // и по-настоящему закрываем конекшен
        }
    }

    /**
     * Method to return connection from taken to free.
     *
     * @param connection - connection to return.
     */
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
