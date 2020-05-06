package by.training.service.exception;

/**
 * Exception class to Service layer.
 */
public class ServiceException extends Exception {

    private static final long serialVersionUID = 8224468405137657183L;
    public static final int DAO_LAYER_ERROR = 500;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;

    /**
     * Own error code for Service layer.
     */
    private final int code;

    public ServiceException(int code) {
        super();
        this.code = code;
    }

    public ServiceException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }


    public ServiceException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
