package wiki.thin.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.thin.common.util.UriUtil;
import wiki.thin.module.storage.StorageFileManager;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/file")
public class FileController {
    private final StorageFileManager storageFileManager;

    public FileController(StorageFileManager storageFileManager) {
        this.storageFileManager = storageFileManager;
    }

    @GetMapping("/image/{fileId}")
    public void image(@PathVariable Long fileId, HttpServletResponse response) throws IOException {
        final URI uri = storageFileManager.getUri(fileId);
        if (UriUtil.isFile(uri)) {
            final Path path = Paths.get(uri.getPath());
            Files.copy(path, response.getOutputStream());
        } else if (UriUtil.isHttp(uri)) {
            response.sendRedirect(uri.toString());
        }
    }
}
