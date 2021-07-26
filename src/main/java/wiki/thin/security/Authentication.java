package wiki.thin.security;

import wiki.thin.entity.User;

import java.io.Serializable;

/**
 * @author Beldon
 */
public class Authentication implements Serializable {

    public static final Authentication GUEST = new Authentication(AuthType.GUEST, null);

    private final AuthType authType;

    private final User user;

    private Authentication(AuthType authType, User user) {
        this.user = user;
        this.authType = authType;
    }

    public static Authentication authentication(User user) {
        return new Authentication(AuthType.AUTHENTICATED, user);
    }

    public static Authentication rememberMe(User user) {
        return new Authentication(AuthType.REMEMBER_ME, user);
    }

    public boolean isRememberMe() {
        return authType.isRememberMe();
    }

    public boolean isAuthenticated() {
        return authType.isAuthenticated();
    }

    public boolean isGuest() {
        return authType.isGuest();
    }

    public User user() {
        return user;
    }
}
