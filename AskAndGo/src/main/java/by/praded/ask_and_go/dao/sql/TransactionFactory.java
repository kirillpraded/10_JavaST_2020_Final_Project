package by.praded.ask_and_go.dao.sql;

import by.praded.ask_and_go.dao.Transaction;
import by.praded.ask_and_go.dao.exception.ConnectionPoolException;

/**
 * @author Kiryl Praded
 * 21.01.2021
 * <p>
 * Transaction factory for creating new transactions.
 * @see Transaction
 * @see TransactionImpl
 */
public class TransactionFactory {
    /**
     * Instance of the transaction factory for singleton pattern.
     */
    private static TransactionFactory instance;

    /**
     * Private hidden class constructor.
     */
    private TransactionFactory() {
    }

    /**
     * Method to get instance of the transaction factory {@link TransactionFactory#instance}.
     *
     * @return singleton value of transaction factory.
     */
    public static TransactionFactory getInstance() {
        if (instance == null) {
            instance = new TransactionFactory();
        }
        return instance;
    }

    /**
     * Method to create transactions.
     *
     * @param autocommit - boolean value to turn on or off autocommiting.
     * @return created transaction.
     * @throws ConnectionPoolException - when the connection pool throws.
     */
    public Transaction createTransaction(boolean autocommit) throws ConnectionPoolException {
        return new TransactionImpl(autocommit);
    }
}
