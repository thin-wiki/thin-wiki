package wiki.thin.web.controller.admin;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.thin.common.AppSystem;
import wiki.thin.common.upgrade.SystemUpgrader;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.web.vo.ResponseVO;

import java.io.IOException;

/**
 * @author beldon
 */
@RequestMapping("/api/admin/upgrade")
@RestController
@NeedAuth
public class UpgradeAdminController {

    private final SystemUpgrader systemUpgrader;

    public UpgradeAdminController(SystemUpgrader systemUpgrader) {
        this.systemUpgrader = systemUpgrader;
    }

    @PutMapping
    public ResponseVO upgrade() {
        if (systemUpgrader.isInstalled()) {
            return ResponseVO.success("已安装");
        }
        try {
            systemUpgrader.downloadNewest();
            systemUpgrader.install();
        } catch (IOException e) {
            return ResponseVO.error(e.getMessage());
        }
        return ResponseVO.success();
    }

    @PutMapping("/restart")
    public ResponseVO restart() throws IOException {
        AppSystem.restart();
        return ResponseVO.success();
    }
}
