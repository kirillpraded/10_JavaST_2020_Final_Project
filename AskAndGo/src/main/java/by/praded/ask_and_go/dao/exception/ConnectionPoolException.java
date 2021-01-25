package by.praded.ask_and_go.dao.exception;

/**
 * @author Kiryl Praded
 * 16.12.2020
 * <p>
 * Custom exception for cases of exceptions in interaction with connection pool.
 * @see by.praded.ask_and_go.dao.pool.ConnectionPool
 */
public class ConnectionPoolException extends Exception {
    /**
     * {@inheritDoc}
     */
    public ConnectionPoolException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    protected ConnectionPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
