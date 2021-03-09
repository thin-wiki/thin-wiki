package wiki.thin.common.upgrade;

/**
 * @author beldon
 */
public class SystemUpgrader {

    private static final SystemUpgrader INSTANCE = new SystemUpgrader();

    private SystemUpgrader() {

    }

    public static SystemUpgrader getInstance() {
        return INSTANCE;
    }


}
