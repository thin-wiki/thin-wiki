package wiki.thin.exception;

/**
 * @author beldon
 */
public class DecryptException extends RuntimeException {
    public DecryptException(Throwable e) {
        super(e);
    }
}
