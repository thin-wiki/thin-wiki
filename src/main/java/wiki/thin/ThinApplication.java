package wiki.thin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import wiki.thin.install.InstallApplication;
import wiki.thin.install.InstallManager;

/**
 * @author Beldon
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableAsync
@MapperScan(annotationClass = Repository.class)
@ServletComponentScan
public class ThinApplication {

    public static void main(String[] args) {
        SpringApplication app;
        if (InstallManager.isInstalled()) {
            app = new SpringApplication(ThinApplication.class);
        } else {
            app = new SpringApplication(InstallApplication.class);
            app.setAdditionalProfiles("install");
        }
        app.addListeners(new ApplicationPidFileWriter("app.pid"));
        app.run(args);
    }

}
