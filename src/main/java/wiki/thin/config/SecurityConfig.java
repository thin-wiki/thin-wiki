package wiki.thin.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import wiki.thin.mapper.UserMapper;
import wiki.thin.security.SecurityFilter;
import wiki.thin.security.remember.AesTokenRememberMeService;
import wiki.thin.security.remember.RememberMeFilter;
import wiki.thin.security.remember.RememberMeService;
import wiki.thin.service.AppConfigService;

/**
 * 配置.
 *
 * @author Beldon
 */
@Configuration
public class SecurityConfig {

    //    @Bean
//    public FilterRegistrationBean securityFilter() {
//        FilterRegistrationBean<SecurityFilter> registrationBean = new FilterRegistrationBean<>(new SecurityFilter());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return registrationBean;
//    }
//
//    @Bean
//    public FilterRegistrationBean rememberMeFilter(RememberMeService rememberMeService) {
//        RememberMeFilter rememberMeFilter = new RememberMeFilter(rememberMeService);
//        FilterRegistrationBean<RememberMeFilter> registrationBean = new FilterRegistrationBean<>(rememberMeFilter);
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE + 2);
//        return registrationBean;
//    }
//
    @Bean
    public RememberMeService rememberMeService(UserMapper userMapper, AppConfigService appConfigService) {
        return new AesTokenRememberMeService(userMapper, appConfigService);
    }

}
