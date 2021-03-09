package wiki.thin.exception;

/**
 * @author beldon
 */
public class EncryptException extends RuntimeException {
    public EncryptException(Throwable e) {
        super(e);
    }
}
