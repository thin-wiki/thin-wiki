package wiki.thin.web.controller;

import reactor.core.publisher.Mono;
import wiki.thin.entity.User;
import wiki.thin.security.Authentication;
import wiki.thin.security.AuthenticationContextHolder;

import java.util.function.Function;

/**
 * controller base
 *
 * @author Beldon
 */
public abstract class BaseController {
    protected String getTemplate(String path) {
        return "theme/default/" + path;
    }

    protected boolean isLogin() {
//        return AuthenticationContextHolder.isLogin();
        return false;
    }

    protected Long currentUserId() {
//        final User user = AuthenticationContextHolder.currentUser();
//        if (user != null) {
//            return user.getId();
//        }
        return null;
    }

    protected Mono<String> currentAccount() {
        return AuthenticationContextHolder.getAuthentication().map(authentication -> authentication.user().getAccount());
    }

}
