package wiki.thin.security.auth;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import reactor.core.publisher.Mono;
import wiki.thin.exception.NoLoginException;
import wiki.thin.security.Authentication;
import wiki.thin.security.AuthenticationContextHolder;
import wiki.thin.security.annotation.NeedAuth;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * api interceptor.
 *
 * @author Beldon
 */
public class AuthInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
//        if (!AuthenticationContextHolder.isLogin()) {
//            throw new NoLoginException();
//        }
//        final var needAuth = getNeedAuthAnnotation(invocation);
//        if (needAuth != null && !AuthenticationContextHolder.isAuthenticated()) {
//            throw new NoAuthException();
//        }

//        System.out.println(abcd.isDisposed());
        return AuthenticationContextHolder.getAuthentication()
                .doOnNext(authentication -> {
                    if (Authentication.GUEST.equals(authentication)) {
                        throw new NoLoginException();
                    }
                }).then((Mono<Object>) invocation.proceed());

//        return invocation.proceed();
    }

    private NeedAuth getNeedAuthAnnotation(MethodInvocation invocation) {
        var needAuth = invocation.getMethod().getAnnotation(NeedAuth.class);
        if (needAuth == null) {
            needAuth = Objects.requireNonNull(invocation.getThis()).getClass().getAnnotation(NeedAuth.class);
        }
        return needAuth;
    }
}
