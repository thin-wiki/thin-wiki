package wiki.thin.security.auth;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import wiki.thin.security.annotation.NeedLogin;

/**
 * auth config.
 *
 * @author Beldon
 */
@Configuration
public class AuthConfiguration {
    @Bean
    public DefaultPointcutAdvisor authPointcutAdvisor() {
        return new DefaultPointcutAdvisor(new AnnotationMatchingPointcut(Controller.class, NeedLogin.class, true),
                new AuthInterceptor());
    }
}
