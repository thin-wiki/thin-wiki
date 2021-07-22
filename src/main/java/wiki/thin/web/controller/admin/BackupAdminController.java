//package wiki.thin.web.controller.admin;
//
//import org.springframework.web.bind.annotation.*;
//import wiki.thin.backup.BackupService;
//import wiki.thin.security.annotation.NeedAuth;
//import wiki.thin.web.vo.ResponseVO;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author beldon
// */
//@RequestMapping("/api/admin/backup")
//@RestController
//@NeedAuth
//public class BackupAdminController {
//    private final BackupService backupService;
//
//    public BackupAdminController(BackupService backupService) {
//        this.backupService = backupService;
//    }
//
//    @GetMapping
//    public ResponseVO list() throws IOException {
//        return ResponseVO.successWithData(backupService.list());
//    }
//
//    @PostMapping
//    public ResponseVO backup() throws IOException {
//        backupService.backup();
//        return ResponseVO.success();
//    }
//
//    @DeleteMapping("/{fileName}")
//    public ResponseVO delete(@PathVariable("fileName") String fileName) throws IOException {
//        backupService.delete(fileName);
//        return ResponseVO.success();
//    }
//
//    @GetMapping("/{fileName}")
//    public void download(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
//        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//        backupService.download(fileName, response.getOutputStream());
//    }
//}
