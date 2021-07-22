package wiki.thin.config;

import org.springframework.context.annotation.Configuration;

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
//    @Bean
//    public RememberMeService rememberMeService(UserMapper userMapper, AppConfigService appConfigService) {
//        return new AesTokenRememberMeService(userMapper, appConfigService);
//    }

}
