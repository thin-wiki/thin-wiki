package wiki.thin.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * web config.
 *
 * @author Beldon
 */
@Configuration
public class WebConfig implements WebFluxConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/theme/**").addResourceLocations("file:./theme/", "classpath:/theme/", "classpath:/templates/theme/");
        registry.addResourceHandler("/static/**").addResourceLocations("file:./static/", "classpath:/templates/static/");
//        registry.addResourceHandler("/admin/**").addResourceLocations(new ClassPathResource("/static/admin/"));
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(apiInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/pub/**");
//    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (factory -> {
            var errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/");
            factory.addErrorPages(errorPage404);
        });

    }

//    @Bean
//    public ApiInterceptor apiInterceptor() {
//        return new ApiInterceptor();
//    }

}
