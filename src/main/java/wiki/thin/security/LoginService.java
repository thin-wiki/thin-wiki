package wiki.thin.security;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import wiki.thin.entity.User;

import java.util.Optional;

/**
 * @author Beldon
 */
public interface LoginService {
    Optional<Authentication> autoLogin(ServerHttpRequest request, ServerHttpResponse response);

    void login(ServerWebExchange exchange, User user);

    void logout(ServerWebExchange exchange);
}
