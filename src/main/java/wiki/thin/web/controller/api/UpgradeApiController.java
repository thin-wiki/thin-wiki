package wiki.thin.web.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.thin.common.AppSystem;
import wiki.thin.common.upgrade.SystemUpgrader;
import wiki.thin.web.vo.ResponseVO;

import java.io.IOException;

/**
 * @author beldon
 */
@RequestMapping("/api/upgrade")
@RestController
public class UpgradeApiController {

    private final SystemUpgrader systemUpgrader;

    public UpgradeApiController(SystemUpgrader systemUpgrader) {
        this.systemUpgrader = systemUpgrader;
    }

    @GetMapping("/has_update")
    public ResponseVO hasUpdate() {
        final boolean hasNewVersion = systemUpgrader.hasNewVersion();
        return ResponseVO.successWithData(hasNewVersion);
    }

    @GetMapping("/download")
    public ResponseVO download() throws IOException {
        systemUpgrader.downloadNewest();
        return ResponseVO.success();
    }

    @GetMapping("/downloaded")
    public ResponseVO downloaded() {
        return ResponseVO.successWithData(systemUpgrader.isDownloaded());
    }

    @GetMapping("/install")
    public ResponseVO install() throws IOException {
        systemUpgrader.install();
        return ResponseVO.success();
    }

    @GetMapping("/restart")
    public ResponseVO restart() throws IOException {
        AppSystem.restart();
        return ResponseVO.success();
    }
}
