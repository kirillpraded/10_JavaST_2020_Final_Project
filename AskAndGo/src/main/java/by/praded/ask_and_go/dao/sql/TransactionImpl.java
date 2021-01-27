package by.praded.ask_and_go.dao.sql;

import by.praded.ask_and_go.dao.*;
import by.praded.ask_and_go.dao.pool.ConnectionPool;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author Kiryl Praded
 * 17.12.2020
 * <p>
 * Implementation of the transaction for SQL data storage.
 * @see Transaction
 */
public class TransactionImpl implements Transaction {
    /**
     * Logger for event logging.
     */
    private static final Logger logger = LogManager.getLogger(TransactionImpl.class);

    /**
     * Connection to the database.
     */
    private final Connection connection;

    /**
     * Map of data access objects.
     */
    private final Map<AvailableDao, BaseDaoImpl> daoRepo;

    /**
     * Transaction constructor.
     *
     * @param autocommit - boolean value to turn on or off autocommiting.
     * @throws ConnectionPoolException - when the connection pool throws.
     */
    public TransactionImpl(boolean autocommit) throws ConnectionPoolException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connection = connectionPool.takeConnection();
        try {
            connection.setAutoCommit(autocommit);
        } catch (SQLException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }
        daoRepo = new EnumMap<>(AvailableDao.class);
        //при создании транзакции автоматически создаются 6 дао, теряем в памяти, создавая сразу 6 объектов
        daoRepo.put(AvailableDao.USER, new UserDaoImpl(connection));
        daoRepo.put(AvailableDao.ANSWER, new AnswerDaoImpl(connection));
        daoRepo.put(AvailableDao.CATEGORY, new CategoryDaoImpl(connection));
        daoRepo.put(AvailableDao.QUESTION, new QuestionDaoImpl(connection));
        daoRepo.put(AvailableDao.TAG, new TagDaoImpl(connection));
    }

    /**
     * Method to create dao.
     *
     * @param key    - class of the dao to create.
     * @param <Type> - type of the dao to create.
     * @return created dao of given type.
     * @see Dao
     */
    @Override
    @SuppressWarnings("unchecked")
    public <Type extends Dao<?>> Type createDao(AvailableDao key) {
        return (Type) daoRepo.get(key);
    }

    /**
     * Method to commit the transaction.
     *
     * @throws ConnectionPoolException - may be thrown during interaction with connection.
     */
    @Override
    public void commit() throws ConnectionPoolException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("It's impossible to commit transaction.", e);
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }

    /**
     * Method to rollback the transaction.
     *
     * @throws ConnectionPoolException - may be thrown during interaction with connection.
     */
    @Override
    public void rollback() throws ConnectionPoolException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("It's impossible to rollback transaction.", e);
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }

    /**
     * Method to close the transaction.
     *
     * @throws ConnectionPoolException - may be thrown during interaction with connection.
     */
    @Override
    public void close() throws ConnectionPoolException {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            logger.error("It's impossible to close transaction.", e);
            throw new ConnectionPoolException(e.getMessage(), e);
        }
    }
}
