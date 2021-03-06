package wiki.thin.security.auth;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import wiki.thin.exception.NoAuthException;
import wiki.thin.exception.NoLoginException;
import wiki.thin.security.AuthenticationContextHolder;
import wiki.thin.security.annotation.NeedAuth;

/**
 * api interceptor.
 *
 * @author Beldon
 */
public class AuthInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (!AuthenticationContextHolder.isLogin()) {
            throw new NoLoginException();
        }
        final NeedAuth needAuth = invocation.getMethod().getAnnotation(NeedAuth.class);
        if (needAuth != null && !AuthenticationContextHolder.isAuthenticated()) {
            throw new NoAuthException();
        }
        return invocation.proceed();
    }
}
