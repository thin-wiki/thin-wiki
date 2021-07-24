package wiki.thin.web.controller;

import wiki.thin.entity.User;
import wiki.thin.security.AuthenticationContextHolder;

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

    protected String currentAccount() {
//        final User user = AuthenticationContextHolder.currentUser();
//        if (user != null) {
//            return user.getAccount();
//        }
        return null;
    }
}
