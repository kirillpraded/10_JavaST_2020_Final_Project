package by.praded.ask_and_go.dao.exception;

/**
 * @author Kiryl Praded
 * 18.12.2020
 * <p>
 * Custom exception for cases of exception in interaction with daos.
 * @see by.praded.ask_and_go.dao.AnswerDao
 * @see by.praded.ask_and_go.dao.CategoryDao
 * @see by.praded.ask_and_go.dao.QuestionDao
 * @see by.praded.ask_and_go.dao.TagDao
 * @see by.praded.ask_and_go.dao.UserDao
 * @see by.praded.ask_and_go.dao.Dao
 */
public class DaoException extends Exception {
    /**
     * {@inheritDoc}
     */
    public DaoException() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * {@inheritDoc}
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * {@inheritDoc}
     */
    public DaoException(Throwable cause) {
        super(cause);
    }

    /**
     * {@inheritDoc}
     */
    protected DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
