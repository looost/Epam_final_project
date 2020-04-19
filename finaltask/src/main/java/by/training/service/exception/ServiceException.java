package by.training.service.exception;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 8224468405137657183L;
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
