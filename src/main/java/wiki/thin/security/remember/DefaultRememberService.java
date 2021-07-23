package wiki.thin.security.remember;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerWebExchange;
import wiki.thin.entity.User;
import wiki.thin.security.Authentication;
import wiki.thin.security.LoginService;
import wiki.thin.security.SecurityConstant;

import java.util.Optional;

/**
 * @author Beldon
 */
@Service
public class DefaultRememberService implements LoginService {
    @Override
    public Optional<Authentication> autoLogin(ServerHttpRequest request, ServerHttpResponse response) {
        return Optional.empty();
    }

    @Override
    public void login(ServerWebExchange exchange, User user) {
        exchange.getSession().subscribe(webSession ->
                webSession.getAttributes()
                        .put(SecurityConstant.SESSION_AUTHENTICATION, Authentication.authentication(user))
        );
    }

    @Override
    public void logout(ServerWebExchange exchange) {
        exchange.getSession().subscribe(webSession ->
                webSession.getAttributes().remove(SecurityConstant.SESSION_AUTHENTICATION)
        );
    }


}
