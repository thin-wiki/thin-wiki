package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import wiki.thin.common.bean.SystemConfig;
import wiki.thin.core.env.EnvManager;
import wiki.thin.common.upgrade.SystemUpgrader;
import wiki.thin.constant.enums.ResourceBaseUrlType;
import wiki.thin.service.AppConfigService;

import static wiki.thin.constant.ConfigConstant.*;

/**
 * @author Beldon
 */
@Service
@Lazy(value = false)
public class SystemVar extends BaseVariable {

    private final AppConfigService appConfigService;
    private final SystemUpgrader systemUpgrader;

    protected SystemVar(AppConfigService appConfigService, SystemUpgrader systemUpgrader) {
        super("sysVar");
        this.appConfigService = appConfigService;
        this.systemUpgrader = systemUpgrader;
    }

    public SystemConfig systemConfig() {
        SystemConfig config = new SystemConfig();
        config.setWebSiteName(appConfigService.getSystemConfigValue(SYS_WEBSITE_NAME, () -> SYS_DEFAULT_WEBSITE_NAME));
        config.setWebSiteDescription(appConfigService.getSystemConfigValue(SYS_WEBSITE_DESCRIPTION, () -> SYS_DEFAULT_WEBSITE_DESCRIPTION));
        config.setWebSiteKeywords(appConfigService.getSystemConfigValue(SYS_WEBSITE_KEYWORDS, () -> SYS_DEFAULT_WEBSITE_KEYWORDS));
        config.setResourceBaseUrlType(appConfigService.getSystemConfigValue(SYS_RESOURCE_BASE_URL_TYPE_KEY, ResourceBaseUrlType.LOCAL::getType));
        return config;
    }

    public String getVersion() {
        return EnvManager.BUILD_INFO.getVersion();
    }

    public String getNewestVersion() {
        return systemUpgrader.getNewestVersion();
    }

    public boolean isInstall() {
        return systemUpgrader.isInstalled();
    }

    public boolean hasNewVersion() {
        return systemUpgrader.hasNewVersion();
    }
}
