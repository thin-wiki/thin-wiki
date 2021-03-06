package wiki.thin.common;

import com.restart4j.ApplicationRestart;

/**
 * @author beldon
 */
public class AppSystem {

    /**
     * restart app
     */
    public static void restart() {
        final ApplicationRestart appRestart = ApplicationRestart.builder().build();
        appRestart.restartApp();
    }
}
