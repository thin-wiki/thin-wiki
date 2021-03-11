package wiki.thin.install;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.MybatisLanguageDriverAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import wiki.thin.config.WebConfig;
import wiki.thin.service.PasswordService;
import wiki.thin.service.impl.PasswordServiceImpl;

/**
 * @author beldon
 */
@SpringBootApplication(exclude = {
        MybatisLanguageDriverAutoConfiguration.class,
        MybatisAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class,
        DataSourceAutoConfiguration.class
})
@EnableConfigurationProperties
@Import(WebConfig.class)
@Profile("install")
public class InstallApplication {

    @Bean
    public FlywayProperties flywayProperties() {
        return new FlywayProperties();
    }

    @Bean
    public PasswordService passwordService() {
        return new PasswordServiceImpl();
    }
}
