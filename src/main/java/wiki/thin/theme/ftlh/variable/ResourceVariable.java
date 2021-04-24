package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wiki.thin.core.env.EnvManager;
import wiki.thin.constant.ConfigConstant;
import wiki.thin.service.AppConfigService;

import static wiki.thin.constant.enums.ResourceBaseUrlType.*;

/**
 * @author Beldon
 */
@Service
@Lazy(value = false)
public class ResourceVariable extends BaseVariable {

    private static final String LOCAL_RESOURCE_BASE_URL = "/";
    private static final String JSDELIVER_BASE_URL_TEMPLAGE = "https://cdn.jsdelivr.net/gh/thin-wiki/thin-wiki@{TAG}/src/main/resources/templates/";
    private static final String SNAPSHOT_KEY = "SNAPSHOT";
    private String jsdeliverBaseUrl;

    private final AppConfigService appConfigService;

    public ResourceVariable(AppConfigService appConfigService) {
        super("resVar");
        this.appConfigService = appConfigService;
        jsdeliverBaseUrl = getJsdeliveBasePath();
    }

    public String getResourceBashPath() {
        final String baseUrlType = appConfigService.getSystemConfigValue(
                ConfigConstant.SYS_RESOURCE_BASE_URL_TYPE_KEY, LOCAL::getType);

        if (!StringUtils.hasText(baseUrlType) || LOCAL.getType().equals(baseUrlType)) {
            return LOCAL_RESOURCE_BASE_URL;
        }

        if (CUSTOM.getType().equals(baseUrlType)) {
            return appConfigService.getSystemConfigValue(
                    ConfigConstant.SYS_RESOURCE_BASE_URL_KEY, () -> LOCAL_RESOURCE_BASE_URL);
        }

        if (JSDELIVR.getType().equals(baseUrlType)) {
            return jsdeliverBaseUrl;
        }

        return LOCAL_RESOURCE_BASE_URL;
    }

    private String getJsdeliveBasePath() {
        final String version = EnvManager.BUILD_INFO.getVersion();
        String tag = version;
        if (!StringUtils.hasText(version) || version.contains(SNAPSHOT_KEY)) {
            tag = "main";
        }
        return JSDELIVER_BASE_URL_TEMPLAGE.replace("{TAG}", tag);
    }
}
