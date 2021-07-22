package wiki.thin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import wiki.thin.core.startup.StartupApplication;

/**
 * @author Beldon
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
@ServletComponentScan
@EnableR2dbcAuditing
public class ThinApplication {

    public static void main(String[] args) {
        StartupApplication.run(args, ThinApplication.class);
    }

}
