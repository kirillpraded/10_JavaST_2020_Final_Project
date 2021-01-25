package by.praded.ask_and_go.service.exception;

/**
 * @author Kiryl Praded
 * 18.12.2020
 * <p>
 * Custom exception for the case when the user enters the wrong username/password pair.
 */
public class BadCredentialsException extends Exception {

    /**
     * {@inheritDoc}
     */
    public BadCredentialsException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public BadCredentialsException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public BadCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public BadCredentialsException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    protected BadCredentialsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
