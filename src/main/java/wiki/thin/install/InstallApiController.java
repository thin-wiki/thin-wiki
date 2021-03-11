package wiki.thin.install;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import wiki.thin.common.AppSystem;
import wiki.thin.install.bean.AdminAccount;
import wiki.thin.install.bean.DatabaseData;
import wiki.thin.install.bean.Website;
import wiki.thin.web.vo.ResponseVO;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author beldon
 */
@RequestMapping("/api")
@RestController
@Profile("install")
@Slf4j
public class InstallApiController {
    private static final ScheduledExecutorService SCHEDULED_THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(1, new ThreadFactory() {

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r);
        }
    });
    private final InstallManager installManager;

    public InstallApiController(InstallManager installManager) {
        this.installManager = installManager;
    }

    @PutMapping("/token")
    public ResponseVO checkToken(@RequestParam("token") String token, HttpServletRequest request) {
        final boolean checkResult = installManager.checkToken(token, request);
        return ResponseVO.successWithData(checkResult);
    }

    @PutMapping("/database")
    public ResponseVO database(@RequestBody DatabaseData databaseData, HttpServletRequest request) throws IOException {
        if (!installManager.passCheckToken(request)) {
            return ResponseVO.error("token 未校验");
        }

        final boolean checkResult = installManager.checkConnect(databaseData);
        if (checkResult) {
            installManager.createConfigFile(databaseData);
        }

        return ResponseVO.successWithData(checkResult);
    }

    @PutMapping("/website")
    public ResponseVO website(@RequestBody Website website, HttpServletRequest request) {
        if (!installManager.passCheckToken(request)) {
            return ResponseVO.error("token 未校验");
        }
        installManager.setWebsiteConfig(website);
        return ResponseVO.success();
    }

    @PutMapping("/account")
    public ResponseVO account(@RequestBody AdminAccount adminAccount, HttpServletRequest request) {
        if (!installManager.passCheckToken(request)) {
            return ResponseVO.error("token 未校验");
        }
        installManager.setAccount(adminAccount);
        return ResponseVO.success();
    }

    @PutMapping("/restart")
    public ResponseVO restart(HttpServletRequest request) {
        if (!installManager.passCheckToken(request)) {
            return ResponseVO.error("token 未校验");
        }
        SCHEDULED_THREAD_POOL_EXECUTOR.schedule(() -> {
            try {
                installManager.removeAuthFile();
            } catch (IOException e) {
                log.error("remove auth file error", e);
            }
            AppSystem.restart();
        }, 1, TimeUnit.SECONDS);
        return ResponseVO.success();
    }

}


