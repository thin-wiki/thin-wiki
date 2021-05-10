package wiki.thin.config;

import net.bull.javamelody.SessionListener;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import wiki.thin.security.ApiInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * web config.
 *
 * @author Beldon
 */
@Configuration
public class WebConfig implements WebMvcConfigurer, ServletContextInitializer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/theme/**").addResourceLocations("file:./theme/", "classpath:/theme/");
        registry.addResourceHandler("/static/**").addResourceLocations("file:./static/", "classpath:/templates/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiInterceptor()).addPathPatterns("/api/**");
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return (factory -> {
            var errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/");
            factory.addErrorPages(errorPage404);
        });

    }

    @Bean
    public ApiInterceptor apiInterceptor() {
        return new ApiInterceptor();
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(SessionListener.class);
    }
}
