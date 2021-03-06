package wiki.thin.security;

import wiki.thin.entity.User;

import java.util.Optional;

/**
 * authentication context holder.
 *
 * @author Beldon
 */
public class AuthenticationContextHolder {
    private static final ThreadLocal<Authentication> CONTEXT_HOLDER = new ThreadLocal<>();

    private AuthenticationContextHolder() {

    }

    public static Optional<Authentication> getAuthentication() {
        return Optional.ofNullable(CONTEXT_HOLDER.get());
    }

    public static boolean isAuthenticated() {
        final var authentication = CONTEXT_HOLDER.get();
        return authentication != null && authentication.isAuthenticated();
    }

    public static boolean isRememberMe() {
        final var authentication = CONTEXT_HOLDER.get();
        return authentication != null && authentication.isRememberMe();
    }

    public static boolean isGuest() {
        final var authentication = CONTEXT_HOLDER.get();
        return authentication != null && authentication.isGuest();
    }

    public static boolean isLogin() {
        return !isGuest();
    }

    public static User currentUser() {
        final var authentication = CONTEXT_HOLDER.get();
        if (authentication == null) {
            return null;
        }
        return authentication.user();
    }

    public static boolean noAuthentication() {
        return CONTEXT_HOLDER.get() == null;
    }

    public static void setAuthentication(Authentication authentication) {
        CONTEXT_HOLDER.set(authentication);
    }

    public static void removeAuthentication() {
        CONTEXT_HOLDER.remove();
    }
}
