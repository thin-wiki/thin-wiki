package wiki.thin.common;


import lombok.extern.slf4j.Slf4j;
import wiki.thin.core.restart.ApplicationRestart;

/**
 * @author beldon
 */
@Slf4j
public class AppSystem {

    private AppSystem() {

    }

    /**
     * restart app
     */
    public static void restart() {
        ApplicationRestart.restartApp();
    }
}
