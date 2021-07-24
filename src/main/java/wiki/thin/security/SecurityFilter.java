package wiki.thin.security;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * security filter.
 *
 * @author Beldon
 */
@Component
public class SecurityFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        SessionHandler sessionHandler = new SessionHandler();
        return exchange.getSession()
                .doOnNext(sessionHandler::setWebSession)
                .then(chain.filter(exchange).contextWrite(sessionHandler));

    }

}
