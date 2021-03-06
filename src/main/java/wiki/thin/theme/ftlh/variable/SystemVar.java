package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import wiki.thin.common.bean.SystemConfig;
import wiki.thin.service.AppConfigService;

import static wiki.thin.constant.ConfigConstant.*;

/**
 * @author Beldon
 */
@Service
@Lazy(value = false)
public class SystemVar extends BaseVariable {

    private final AppConfigService appConfigService;

    protected SystemVar(AppConfigService appConfigService) {
        super("sysVar");
        this.appConfigService = appConfigService;
    }

    public SystemConfig systemConfig() {
        SystemConfig config = new SystemConfig();
        config.setWebSiteName(appConfigService.getSystemConfigValue(SYS_WEBSITE_NAME, () -> SYS_DEFAULT_WEBSITE_NAME));
        config.setWebSiteDescription(appConfigService.getSystemConfigValue(SYS_WEBSITE_DESCRIPTION, () -> SYS_DEFAULT_WEBSITE_DESCRIPTION));
        config.setWebSiteKeywords(appConfigService.getSystemConfigValue(SYS_WEBSITE_KEYWORDS, () -> SYS_DEFAULT_WEBSITE_KEYWORDS));
        return config;
    }
}
