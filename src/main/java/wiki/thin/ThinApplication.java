package wiki.thin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import wiki.thin.core.startup.StartupApplication;

/**
 * @author Beldon
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
@MapperScan(annotationClass = Repository.class)
@ServletComponentScan
@EnableJpaAuditing
public class ThinApplication {

    public static void main(String[] args) {
        StartupApplication.run(args, ThinApplication.class);
    }

}
