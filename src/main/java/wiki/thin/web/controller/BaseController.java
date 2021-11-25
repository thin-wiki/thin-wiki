package wiki.thin.web.controller;

import cn.dev33.satoken.stp.StpUtil;

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
        return StpUtil.isLogin();
    }

    protected Long currentUserId() {
        return StpUtil.getLoginIdAsLong();
    }

    protected String currentAccount() {
        return null;
    }
}
