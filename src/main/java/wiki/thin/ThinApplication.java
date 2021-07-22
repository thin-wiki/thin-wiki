package wiki.thin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import wiki.thin.core.startup.StartupApplication;

/**
 * @author Beldon
 */
@SpringBootApplication
//@EnableConfigurationProperties
//@EnableR2dbcAuditing
public class ThinApplication {

    public static void main(String[] args) {
        StartupApplication.run(args, ThinApplication.class);
    }

}
