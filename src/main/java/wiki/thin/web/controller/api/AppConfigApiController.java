package wiki.thin.web.controller.api;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.thin.common.bean.SystemConfig;
import wiki.thin.constant.ConfigConstant;
import wiki.thin.service.AppConfigService;
import wiki.thin.web.vo.ResponseVO;

/**
 * @author Beldon
 */
@RequestMapping("/api/config")
@RestController
@Deprecated
public class AppConfigApiController {

    private final AppConfigService appConfigService;

    public AppConfigApiController(AppConfigService appConfigService) {
        this.appConfigService = appConfigService;
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
