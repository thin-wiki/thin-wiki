package wiki.thin.storage.local;

import wiki.thin.common.util.FileUtils;
import wiki.thin.entity.LocalStorage;
import wiki.thin.exception.UnexpectedException;
import wiki.thin.storage.StorageFileType;
import wiki.thin.storage.StorageService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Beldon
 */
public class LocalStorageService implements StorageService {

    private final LocalStorage localStorage;

    public LocalStorageService(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    @Override
    public void saveFile(byte[] data, String relativePath) throws IOException {
        final File file = new File(localStorage.getBasePath(), relativePath);
        final File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            final boolean mkdirSuccess = parentFile.mkdirs();
            if (!mkdirSuccess) {
                throw new UnexpectedException("文件夹创建失败");
            }
        }

        final Path fullFilePath = file.toPath();
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        Files.copy(inputStream, fullFilePath);
    }

    @Override
    public String getRelativePath(StorageFileType storageFileType, String originalFileName) {
        if (StorageFileType.IMAGE.equals(storageFileType)) {
            return "images" + "/" + FileUtils.generateRelativePath(originalFileName);
        }
        return FileUtils.generateRelativePath(originalFileName);
    }

    @Override
    public URI getUri(String relativePath) {
        return new File(localStorage.getBasePath(), relativePath).toURI();
    }

}
