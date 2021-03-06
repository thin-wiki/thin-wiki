package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import wiki.thin.entity.User;
import wiki.thin.security.AuthenticationContextHolder;

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
        return AuthenticationContextHolder.isAuthenticated();
    }

    /**
     * 判断是否是记住我
     *
     * @return is remember me
     */
    public boolean isRememberMe() {
        return AuthenticationContextHolder.isRememberMe();
    }

    /**
     * 判断是否是游客
     *
     * @return is guest
     */
    public boolean isGuest() {
        return AuthenticationContextHolder.isGuest();
    }

    /**
     * 判断用户是否登录
     *
     * @return is login
     */
    public boolean isLogin() {
        return AuthenticationContextHolder.isLogin();
    }

    /**
     * @return current user
     */
    public User getCurrentUser() {
        return AuthenticationContextHolder.currentUser();
    }

}
