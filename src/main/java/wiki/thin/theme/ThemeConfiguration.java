//package wiki.thin.theme;
//
//import org.springframework.aop.ClassFilter;
//import org.springframework.aop.MethodMatcher;
//import org.springframework.aop.Pointcut;
//import org.springframework.aop.support.DefaultPointcutAdvisor;
//import org.springframework.aop.support.annotation.AnnotationClassFilter;
//import org.springframework.aop.support.annotation.AnnotationMethodMatcher;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import wiki.thin.service.AppConfigService;
//
///**
// * @author Beldon
// */
//@Configuration
//public class ThemeConfiguration {
//
//    @Bean
//    public DefaultPointcutAdvisor themePointcutAdvisor(ThemeInterceptor themeInterceptor) {
//        return new DefaultPointcutAdvisor(new Pointcut() {
//            @Override
//            public ClassFilter getClassFilter() {
//                return new AnnotationClassFilter(Controller.class);
//            }
//
//            @Override
//            public MethodMatcher getMethodMatcher() {
//                return new AnnotationMethodMatcher(RequestMapping.class, true);
//            }
//        }, themeInterceptor);
//    }
//
//    @Bean
//    public ThemeInterceptor themeInterceptor(AppConfigService appConfigService) {
//        return new ThemeInterceptor(appConfigService);
//    }
//}
