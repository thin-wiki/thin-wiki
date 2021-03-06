package wiki.thin.common.env;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Beldon
 */
public class EnvManager {

    private static final String BUILD_INFO_FILE = "/META-INF/build-info.properties";
    private static final String GIT_INFO_FILE = "/git.properties";

    public static final BuildInfo BUILD_INFO;

    public static final GitInfo GIT_INFO;

    static {
        Properties buildInfoProperties = new Properties();
        Properties gitInfoProperties = new Properties();
        InputStream buildInfoIs = EnvManager.class.getResourceAsStream(BUILD_INFO_FILE);
        InputStream gitInfoIs = EnvManager.class.getResourceAsStream(GIT_INFO_FILE);
        try (
                buildInfoIs;
                gitInfoIs
        ) {
            if (buildInfoIs != null) {
                buildInfoProperties.load(buildInfoIs);
            }
            if (gitInfoIs != null) {
                gitInfoProperties.load(gitInfoIs);
            }
        } catch (IOException e) {
            //ignore
            e.printStackTrace();
        }

        BUILD_INFO = new BuildInfo(buildInfoProperties);
        GIT_INFO = new GitInfo(gitInfoProperties);
    }

    private EnvManager() {

    }
}
