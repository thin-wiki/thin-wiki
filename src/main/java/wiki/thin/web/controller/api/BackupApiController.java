package wiki.thin.web.controller.api;

import org.springframework.web.bind.annotation.*;
import wiki.thin.backup.BackupService;
import wiki.thin.web.vo.ResponseVO;

import java.io.IOException;

/**
 * @author Beldon
 */
@RequestMapping("/api/backup")
@RestController
@Deprecated
public class BackupApiController {

    private final BackupService backupService;

    public BackupApiController(BackupService backupService) {
        this.backupService = backupService;
    }

    @PostMapping
    public ResponseVO backup() throws IOException {
        backupService.backup();
        return ResponseVO.success();
    }

    @DeleteMapping("/{fileName}")
    public ResponseVO delete(@PathVariable("fileName") String fileName) throws IOException {
        backupService.delete(fileName);
        return ResponseVO.success();
    }
}
