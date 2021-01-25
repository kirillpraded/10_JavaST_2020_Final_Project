package by.praded.ask_and_go.dao;

import by.praded.ask_and_go.dao.exception.ConnectionPoolException;

/**
 * @author Kiryl Praded
 * 18.12.2020
 * <p>
 * Default method for implimentation transaction.
 */
public interface Transaction {
    /**
     * Declaration of the method to create dao.
     *
     * @param key    - class of the dao to create.
     * @param <Type> - type of the dao to create.
     * @return created dao of given type.
     * @see Dao
     */
    <Type extends Dao<?>> Type createDao(Class<Type> key);

    /**
     * Declaration of the method to commit the transaction.
     *
     * @throws ConnectionPoolException - may be thrown during interaction with connection.
     */
    void commit() throws ConnectionPoolException;

    /**
     * Declaration of the method to rollback the transaction.
     *
     * @throws ConnectionPoolException - may be thrown during interaction with connection.
     */
    void rollback() throws ConnectionPoolException;

    /**
     * Declaration of the method to close the transaction.
     *
     * @throws ConnectionPoolException - may be thrown during interaction with connection.
     */
    void close() throws ConnectionPoolException;
}
