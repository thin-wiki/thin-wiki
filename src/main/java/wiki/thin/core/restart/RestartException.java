package wiki.thin.core.restart;

/**
 * @author beldon
 */
public class RestartException extends RuntimeException {
    RestartException(String message) {
        super(message);
    }

    RestartException(String message, Throwable cause) {
        super(message, cause);
    }

    RestartException(Throwable cause) {
        super(cause);
    }
}
