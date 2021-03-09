package wiki.thin.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author beldon
 */
@Component
@Data
@ConfigurationProperties(prefix = "app.upgrade")
public class AppUpgradeProperties {

    /**
     * 升级的 base url
     * private String baseUrl = "https://upgrade.thin.wiki";
     */
    private String baseUrl = "https://raw.sevencdn.com/beldon/upgrade/main";

    /**
     * 最新版本路径
     */
    private String newestVersionCheckUrl = "/newest_version.json";

    /**
     * 版本路径
     */
    private String versionBasePath = "/version";

    /**
     * 版本更新配置文件
     */
    private String versionConfigFile = "config.xml";

}
