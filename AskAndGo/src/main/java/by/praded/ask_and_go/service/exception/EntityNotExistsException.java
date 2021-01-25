package by.praded.ask_and_go.service.exception;

/**
 * @author Kiryl Praded
 * 20.12.2020
 * <p>
 * Custom exception for the case when entity with given identity isn't exists.
 */
public class EntityNotExistsException extends Exception {
    /**
     * {@inheritDoc}
     */
    public EntityNotExistsException() {
    }

    /**
     * {@inheritDoc}
     */
    public EntityNotExistsException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public EntityNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public EntityNotExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    public EntityNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
