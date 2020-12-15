package by.praded.ask_and_go.dao.pool;

import by.praded.ask_and_go.exception.ConnectionPoolException;
import by.praded.ask_and_go.service.util.ResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
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
    private final String driver;
    private final String url;
    private final String login;
    private final String password;
    private final Lock lock;
    private BlockingQueue<WrappedConnection> freeConnections;
    private Set<WrappedConnection> takenConnections;
    private int poolSize;

    private ConnectionPool() throws ConnectionPoolException {
        ResourceManager resourceManager = ResourceManager.getInstance();
        this.driver = resourceManager.findConfigurationValue("database.driver");
        this.url = resourceManager.findConfigurationValue("database.url");
        this.login = resourceManager.findConfigurationValue("database.user");
        this.password = resourceManager.findConfigurationValue("database.password");
        this.lock = new ReentrantLock();

        try {
            this.poolSize = Integer.parseInt(resourceManager.findConfigurationValue("database.poolSize"));
        } catch (NumberFormatException e) {
            poolSize = 100;
            logger.error("Failed to parse pool size, pool size has been set to {}", poolSize, e);
        }
        initializePool();
        logger.info("Connection pool initialized");
    }


    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public void initializePool() throws ConnectionPoolException {
        try {
            Class.forName(driver);
            freeConnections = new ArrayBlockingQueue<>(poolSize);
            takenConnections = new HashSet<>(poolSize);
            /*
            for (int i = 0; i < poolSize; i++) {
                freeConnections.add(new WrappedConnection(DriverManager.getConnection(url, login, password)));
            }
             */
        } catch (ClassNotFoundException e) {
            logger.error("Connection pool initialization failed.", e);
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }

    //TODO нужно синхронизировать или нет
    public Connection takeConnection() throws ConnectionPoolException { // WrappedConnection implements Connection поэтому можно отсюда возвращать обычный Connection
        WrappedConnection connection;
        try {
            lock.lock();
            if (freeConnections.isEmpty()) {
                if (takenConnections.size() < poolSize) {
                    connection = new WrappedConnection(DriverManager.getConnection(url, login, password));
                } else {
                    connection = freeConnections.take();
                }
            } else {
                connection = freeConnections.take();
            }
            takenConnections.add(connection);
            lock.unlock();
        } catch (InterruptedException | SQLException e) {
            lock.unlock();
            logger.error("Failed to take connection from queue.", e);
            Thread.currentThread().interrupt();

            /*
            InterruptedExceptions should either be rethrown - immediately or after cleaning up the method's state
             - or the thread should be re-interrupted by calling Thread.interrupt() even if this is supposed to be a single-threaded application.
              Any other course of action risks delaying thread shutdown and loses the information that the thread was interrupted - probably without finishing its task.
             */
            throw new ConnectionPoolException(e.getMessage(), e);
        }
        return connection;
    }

    public void destroyPool() throws ConnectionPoolException { // уничтожение пула
        clearConnectionQueue();
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

    void freeConnection(WrappedConnection connection) {
        //if (connection.isClosed()) {
        //    throw new SQLException("Attempting to close a closed connection");
        //}
        //if (connection.isReadOnly()) {
        //    connection.setReadOnly(false);
        //}
        //if (!connection.getAutoCommit()) {
        //    connection.setAutoCommit(true);
        //}
        try {
            connection.clearWarnings();
            connection.setAutoCommit(true);
            takenConnections.remove(connection);
            freeConnections.offer(connection);
        } catch (SQLException e) {
            //todo logger
            e.printStackTrace();
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

    /*
    метод закрытия, чтобы постоянно в коде не писать try-catch-finally при закрытии конекшена из пулла - если там wrappedConnection то конекшен
    вернется в пул и при этом закроется statement и resultSet
     */
    /*
    public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Failed to return connection to the pool.");
            throw new ConnectionPoolException(e.getMessage(), e);
        }
        try {
            resultSet.close();
        } catch (SQLException e) {
            logger.error("Failed to close resultSet.");
            throw new ConnectionPoolException(e.getMessage(), e);
        }
        try {
            statement.close();
        } catch (SQLException e) {
            logger.error("Failed to close statement", e);
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }

    // перегрузки для других ситуаций
    public void closeConnection(Connection connection, Statement statement) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Failed to return connection to the pool", e);
            throw new ConnectionPoolException(e.getMessage(), e);
        }
        try {
            statement.close();
        } catch (SQLException e) {
            logger.error("Failed to close statement", e);
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }

    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Failed to return connection to the pool", e);
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }


     */
}
