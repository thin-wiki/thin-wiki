//package wiki.thin.web.controller.api;
//
//import org.springframework.web.bind.annotation.*;
//import wiki.thin.common.AppSystem;
//import wiki.thin.common.upgrade.SystemUpgrader;
//import wiki.thin.web.vo.ResponseVO;
//
//import java.io.IOException;
//
///**
// * @author beldon
// */
//@RequestMapping("/api/upgrade")
//@RestController
//public class UpgradeApiController {
//
//    private final SystemUpgrader systemUpgrader;
//
//    public UpgradeApiController(SystemUpgrader systemUpgrader) {
//        this.systemUpgrader = systemUpgrader;
//    }
//
//    @PutMapping
//    public ResponseVO upgrade() {
//        if (systemUpgrader.isInstalled()) {
//            return ResponseVO.success("已安装");
//        }
//        try {
//            systemUpgrader.downloadNewest();
//            systemUpgrader.install();
//        } catch (IOException e) {
//            return ResponseVO.error(e.getMessage());
//        }
//        return ResponseVO.success();
//    }
//
//    @PutMapping("/restart")
//    public ResponseVO restart() throws IOException {
//        AppSystem.restart();
//        return ResponseVO.success();
//    }
//}
