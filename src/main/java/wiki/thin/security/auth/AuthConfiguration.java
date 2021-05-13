package wiki.thin.security.auth;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationClassFilter;
import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import wiki.thin.security.annotation.NeedLogin;

import java.lang.reflect.Method;

/**
 * auth config.
 *
 * @author Beldon
 */
@Configuration
public class AuthConfiguration {
    @Bean
    public DefaultPointcutAdvisor authPointcutAdvisor() {
        return new DefaultPointcutAdvisor(new AuthPointcut(),
                new AuthInterceptor());
    }

    private static class AuthPointcut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return new AnnotationClassFilter(Controller.class, true);
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new AnnotationMethodMatcher(NeedLogin.class, true) {
                @Override
                public boolean matches(Method method, Class<?> targetClass) {
                    return super.matches(method, targetClass) || AnnotatedElementUtils.hasAnnotation(targetClass, NeedLogin.class);
                }
            };
        }
    }
}
