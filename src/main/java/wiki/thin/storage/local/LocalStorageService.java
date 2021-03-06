package wiki.thin.storage.local;

import wiki.thin.entity.LocalStorage;
import wiki.thin.entity.Storage;
import wiki.thin.entity.StorageFile;
import wiki.thin.exception.UnexpectedException;
import wiki.thin.mapper.StorageFileMapper;
import wiki.thin.storage.BaseStorageService;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 * @author Beldon
 */
public class LocalStorageService extends BaseStorageService {

    private final LocalStorage localStorage;

    private final StorageFileMapper storageFileMapper;

    public LocalStorageService(StorageFileMapper storageFileMapper, Storage storage, LocalStorage localStorage) {
        super(storage, storageFileMapper);
        this.storageFileMapper = storageFileMapper;
        this.localStorage = localStorage;
    }

    @Override
    public void saveFile(byte[] data, String relativePath) throws IOException {
        final File file = new File(localStorage.getBasePath(), relativePath);
        final File parentFile = file.getParentFile();
        final boolean mkdirSuccess = parentFile.mkdirs();
        if (!mkdirSuccess) {
            throw new UnexpectedException("文件夹创建失败");
        }

        final Path fullFilePath = file.toPath();
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        Files.copy(inputStream, fullFilePath);
    }

    @Override
    public URI getUri(Long fileId) {
        final Optional<StorageFile> fileStorageOptional = storageFileMapper.findById(fileId);
        if (fileStorageOptional.isEmpty()) {
            return null;
        }
        return new File(localStorage.getBasePath(), fileStorageOptional.get().getRelativePath()).toURI();
    }

}
