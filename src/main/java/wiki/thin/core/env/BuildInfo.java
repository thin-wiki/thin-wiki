package wiki.thin.core.env;

import lombok.Getter;
import lombok.ToString;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @author Beldon
 */
@Getter
@ToString
public class BuildInfo {
    private final String group;
    private final String artifact;
    private final String name;
    private final Date time;
    private final String version;
    private final String encoding;
    private final String javaSource;
    private final String javaTarget;

    protected BuildInfo(Properties properties) {
        this.group = properties.getProperty("build.group", "unknown");
        this.artifact = properties.getProperty("build.artifact", "unknown");
        this.name = properties.getProperty("build.name", "unknown");
        this.time = formatDate(properties.getProperty("build.time"));
        this.version = properties.getProperty("build.version", "");
        this.encoding = properties.getProperty("build.encoding", "unknown");
        this.javaSource = properties.getProperty("build.java.source", "unknown");
        this.javaTarget = properties.getProperty("build.java.target", "unknown");
    }

    private static Date formatDate(String dataStr) {
        if (dataStr == null) {
            return null;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            return format.parse(dataStr);
        } catch (Exception e) {
            return null;
        }
    }
}
