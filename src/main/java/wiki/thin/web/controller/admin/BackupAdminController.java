package wiki.thin.web.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import wiki.thin.backup.BackupService;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.web.vo.ResponseVO;

import java.io.IOException;

/**
 * @author beldon
 */
@RequestMapping("/api/admin/backup")
@RestController
@NeedAuth
@AllArgsConstructor
public class BackupAdminController {
    private final BackupService backupService;


    @GetMapping
    public Mono<ResponseVO> list() throws IOException {
        return Mono.just(ResponseVO.successWithData(backupService.list()));
    }

    @PostMapping
    public Mono<ResponseVO> backup() {
        return backupService
                .backup()
                .then(Mono.just(ResponseVO.success()));
    }

    @DeleteMapping("/{fileName}")
    public Mono<ResponseVO> delete(@PathVariable("fileName") String fileName) throws IOException {
        return Mono.just(ResponseVO.success()).doFinally(d->{
            try {
                backupService.delete(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
//
//    @GetMapping("/{fileName}")
//    public void download(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
//        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
//        backupService.download(fileName, response.getOutputStream());
//    }
}
