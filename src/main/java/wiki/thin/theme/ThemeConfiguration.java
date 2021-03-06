package wiki.thin.theme;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Beldon
 */
@Configuration
public class ThemeConfiguration {

    @Bean
    public DefaultPointcutAdvisor themePointcutAdvisor(ThemeInterceptor themeInterceptor) {
        return new DefaultPointcutAdvisor(new AnnotationMatchingPointcut(Controller.class, RequestMapping.class, true),
                themeInterceptor);
    }

    @Bean
    public ThemeInterceptor themeInterceptor() {
        return new ThemeInterceptor();
    }
}
