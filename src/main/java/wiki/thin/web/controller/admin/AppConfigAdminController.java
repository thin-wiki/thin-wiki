package wiki.thin.web.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import wiki.thin.common.bean.SystemConfig;
import wiki.thin.constant.ConfigConstant;
import wiki.thin.entity.AppConfig;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.service.AppConfigService;
import wiki.thin.web.vo.ResponseVO;

/**
 * @author beldon
 */
@RequestMapping("/api/admin/config")
@RestController
@NeedAuth
@AllArgsConstructor
public class AppConfigAdminController {
    private final AppConfigService appConfigService;


    @GetMapping("/demo")
    public ResponseVO demo() {
        return ResponseVO.success("success");
    }

    @GetMapping("/system")
    public Mono<ResponseVO<SystemConfig>> getSysConfig() {
        SystemConfig systemConfig = new SystemConfig();
        return get(ConfigConstant.SYS_WEBSITE_NAME).doOnNext(systemConfig::setWebSiteName)
                .then(get(ConfigConstant.SYS_WEBSITE_KEYWORDS)).doOnNext(systemConfig::setWebSiteKeywords)
                .then(get(ConfigConstant.SYS_WEBSITE_DESCRIPTION)).doOnNext(systemConfig::setWebSiteDescription)
                .then(get(ConfigConstant.SYS_WEBSITE_KEYWORDS)).doOnNext(systemConfig::setWebSiteKeywords)
                .thenReturn(ResponseVO.successWithData(systemConfig));
    }

    @PutMapping("/system")
    public Mono<ResponseVO> updateSysConfig(@RequestBody SystemConfig systemConfig) {
        return update(ConfigConstant.SYS_WEBSITE_NAME, systemConfig.getWebSiteName())
                .then(update(ConfigConstant.SYS_WEBSITE_KEYWORDS, systemConfig.getWebSiteKeywords()))
                .then(update(ConfigConstant.SYS_WEBSITE_DESCRIPTION, systemConfig.getWebSiteDescription()))
                .then(update(ConfigConstant.SYS_RESOURCE_BASE_URL_TYPE_KEY, systemConfig.getResourceBaseUrlType()))
                .thenReturn(ResponseVO.success());
    }

    private Mono<String> get(String key) {
        return appConfigService.getSystemConfigValue(key);
    }

    private Mono<AppConfig> update(String key, String value) {
        return appConfigService.updateSysConfig(key, value);
    }
}
