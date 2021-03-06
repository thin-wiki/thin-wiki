package wiki.thin.security.auth;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.stereotype.Controller;
import wiki.thin.security.annotation.NeedLogin;

/**
 * auth advisor.
 *
 * @author Beldon
 */
public class AuthAdvisor implements PointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
        return new AnnotationMatchingPointcut(Controller.class, NeedLogin.class, true);
    }

    @Override
    public Advice getAdvice() {
        return new CacheInterceptor();
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}
