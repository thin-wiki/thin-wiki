package wiki.thin.web.controller.admin;

import org.springframework.web.bind.annotation.*;
import wiki.thin.common.bean.SystemConfig;
import wiki.thin.constant.ConfigConstant;
import wiki.thin.constant.enums.ResourceBaseUrlType;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.service.AppConfigService;
import wiki.thin.web.vo.ResponseVO;

/**
 * @author beldon
 */
@RequestMapping("/api/admin/config")
@RestController
@NeedAuth
public class AppConfigAdminController {
    private final AppConfigService appConfigService;

    public AppConfigAdminController(AppConfigService appConfigService) {
        this.appConfigService = appConfigService;
    }

    @GetMapping("/system")
    public ResponseVO getSysConfig() {
        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setWebSiteName(appConfigService.getSystemConfigValue(ConfigConstant.SYS_WEBSITE_NAME, () -> null));
        systemConfig.setWebSiteKeywords(appConfigService.getSystemConfigValue(ConfigConstant.SYS_WEBSITE_KEYWORDS, () -> null));
        systemConfig.setWebSiteDescription(appConfigService.getSystemConfigValue(ConfigConstant.SYS_WEBSITE_DESCRIPTION, () -> null));
        systemConfig.setResourceBaseUrlType(appConfigService.getSystemConfigValue(ConfigConstant.SYS_RESOURCE_BASE_URL_TYPE_KEY,
                ResourceBaseUrlType.LOCAL::getType));
        return ResponseVO.successWithData(systemConfig);
    }

    @PutMapping("/system")
    public ResponseVO updateSysConfig(@RequestBody SystemConfig systemConfig) {
        appConfigService.updateSysConfig(ConfigConstant.SYS_WEBSITE_NAME, systemConfig.getWebSiteName());
        appConfigService.updateSysConfig(ConfigConstant.SYS_WEBSITE_KEYWORDS, systemConfig.getWebSiteKeywords());
        appConfigService.updateSysConfig(ConfigConstant.SYS_WEBSITE_DESCRIPTION, systemConfig.getWebSiteDescription());
        appConfigService.updateSysConfig(ConfigConstant.SYS_RESOURCE_BASE_URL_TYPE_KEY, systemConfig.getResourceBaseUrlType());
        return ResponseVO.success();
    }
}
