package wiki.thin.security;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Beldon
 */
@Getter
@ToString
public enum AuthType {

    /**
     * guest
     */
    GUEST(true, false, false),

    REMEMBER_ME(false, true, false),

    AUTHENTICATED(false, false, true);

    private final boolean guest;
    private final boolean rememberMe;
    private final boolean authenticated;

    AuthType(boolean guest, boolean rememberMe, boolean authenticated) {
        this.guest = guest;
        this.rememberMe = rememberMe;
        this.authenticated = authenticated;
    }
}
