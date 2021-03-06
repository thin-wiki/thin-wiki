package wiki.thin.web.controller.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wiki.thin.storage.StorageFileManager;
import wiki.thin.web.vo.ResponseVO;

import java.io.IOException;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    private final StorageFileManager storageFileManager;

    public FileUploadController(StorageFileManager storageFileManager) {
        this.storageFileManager = storageFileManager;
    }

    @PostMapping
    public ResponseVO upload(@RequestParam("file") MultipartFile file, @RequestParam(name = "targetId") Long targetId) throws IOException {
        final Long fileId = storageFileManager.store(file, targetId);
        return ResponseVO.successWithData(fileId);
    }

}
