package wiki.thin.security;

import lombok.Setter;
import org.springframework.web.server.WebSession;
import reactor.util.context.Context;

import java.util.Map;
import java.util.function.Function;

/**
 * security filter.
 *
 * @author Beldon
 */
public class SessionHandler implements Function<Context, Context> {

    @Setter
    private WebSession webSession;

    @Override
    public Context apply(Context context) {
        final Map<String, Object> attributes = webSession.getAttributes();
        if (attributes.containsKey(SecurityConstant.SESSION_AUTHENTICATION)) {
            final Authentication authentication = (Authentication) attributes.get(SecurityConstant.SESSION_AUTHENTICATION);
            return AuthenticationContextHolder.setAuthentication(context, authentication);
        }
        return AuthenticationContextHolder.setAuthentication(context, Authentication.GUEST);
    }
}
