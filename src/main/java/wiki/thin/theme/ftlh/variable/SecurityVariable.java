package wiki.thin.theme.ftlh.variable;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import wiki.thin.entity.User;

/**
 * @author Beldon.
 */
@Service
@Lazy(value = false)
public class SecurityVariable extends BaseVariable {

    protected SecurityVariable() {
        super("secVar");
    }

    /**
     * 表示当前用户是否已经登录认证成功了。
     *
     * @return is authenticated
     */
    public boolean isAuthenticated() {
        return StpUtil.isLogin();
    }

    /**
     * 判断是否是记住我
     *
     * @return is remember me
     */
    public boolean isRememberMe() {
        return StpUtil.isLogin();
    }

    /**
     * 判断是否是游客
     *
     * @return is guest
     */
    public boolean isGuest() {
        return StpUtil.isLogin();
    }

    /**
     * 判断用户是否登录
     *
     * @return is login
     */
    public boolean isLogin() {
        return StpUtil.isLogin();
    }

    /**
     * @return current user
     */
    public User getCurrentUser() {
        return null;
    }

}
