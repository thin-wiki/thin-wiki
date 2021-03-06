package wiki.thin.security;

import wiki.thin.entity.User;

import java.io.Serializable;

/**
 * @author Beldon
 */
public class Authentication implements Serializable {

    public static final Authentication GUEST = new Authentication(AuthType.GUEST);

    private final AuthType authType;

    private User user;

    public Authentication(AuthType authType) {
        this.authType = authType;
    }

    public Authentication(AuthType authType, User user) {
        this.user = user;
        this.authType = authType;
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
