package wiki.thin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import wiki.thin.core.startup.StartupApplication;
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
        Class<?> appClass;
        if (InstallManager.isInstalled()) {
            appClass = ThinApplication.class;
        } else {
            appClass = InstallApplication.class;
        }
        StartupApplication.run(args, app -> {
            if (InstallApplication.class.equals(appClass)) {
                app.setAdditionalProfiles("install");
            }
        }, appClass);
    }

}
