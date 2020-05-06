package by.training.dao.exception;

/**
 * Exception class to DAO layer.
 */
public class DaoException extends Exception {

    private static final long serialVersionUID = 6895802017494391490L;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
