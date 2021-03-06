package wiki.thin.constant;

/**
 * @author Beldon
 */
public final class CommonConstant {

    private CommonConstant() {

    }

    /**
     * default cookie key for remember me
     */
    public static final String DEFAULT_REMEMBER_ME_KEY = "remember-me";

    /**
     * default remember me expiration time. default fortnight
     */
    public static final int DEFAULT_REMEMBER_ME_EXPIRY = 60 * 60 * 24 * 14 * 1000;

    /**
     * 记住密码的 jwt issuer
     */
    public static final String DEFAULT_REMEMBER_ME_JWT_ISSUER = "thin-wiki";

    /**
     * @author Beldon
     */
    public static final class Session {
        private Session() {

        }

        public static final String AUTHENTICATION = "Authentication";
    }

}
