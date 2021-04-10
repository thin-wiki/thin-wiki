package wiki.thin.storage;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import wiki.thin.common.util.DateUtils;
import wiki.thin.entity.Storage;
import wiki.thin.entity.StorageFile;
import wiki.thin.mapper.StorageFileMapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Optional;

/**
 * @author Beldon
 */
public abstract class BaseStorageService implements StorageService {
    private static final String POINT = ".";

    private final StorageFileMapper storageFileMapper;

    private final Storage storage;

    protected BaseStorageService(Storage storage, StorageFileMapper storageFileMapper) {
        this.storageFileMapper = storageFileMapper;
        this.storage = storage;
    }

    @Override
    public StoredFile store(MultipartFile file, Long targetId) throws IOException {
        final String originalFilename = file.getOriginalFilename();
        final String relativePath = getRelativePath(StorageFileType.IMAGE, originalFilename);
        saveFile(file.getBytes(), relativePath);

        StorageFile storageFile = new StorageFile();
        storageFile.setTargetId(targetId);
        storageFile.setOriginalFileName(originalFilename);
        storageFile.setSuffix(getSuffix(originalFilename));
        storageFile.setFileSize(file.getSize());
        storageFile.setContentType(file.getContentType());
        storageFile.setRelativePath(relativePath);
        storageFile.setStorageId(storage.getId());
        storageFileMapper.insertSelective(storageFile);
        return new StoredFile(storageFile.getId(), relativePath);
    }

    @Override
    public Long storageId() {
        return storage.getId();
    }

    @Override
    public URI getUri(Long fileId) {
        final Optional<StorageFile> fileStorageOptional = storageFileMapper.findById(fileId);
        if (fileStorageOptional.isEmpty()) {
            return null;
        }
        return getUri(fileStorageOptional.get());
    }

    /**
     * 获取 文件 uri
     *
     * @param file storage file
     * @return 文件 uri
     */
    protected abstract URI getUri(StorageFile file);

    /**
     * 获取文件相对路径
     *
     * @param storageFileType  storage file type
     * @param originalFileName 原文件名
     * @return 文件相对路径
     */
    public String getRelativePath(StorageFileType storageFileType, String originalFileName) {
        return currentDir() + "/" + System.currentTimeMillis() + POINT + getSuffix(originalFileName);
    }

    /**
     * 获取文件后缀
     *
     * @param originalFileName originalFileName
     * @return file name suffix
     */
    protected String getSuffix(String originalFileName) {
        if (!StringUtils.hasText(originalFileName) || !originalFileName.contains(POINT)) {
            return null;
        }

        return originalFileName.substring(originalFileName.lastIndexOf(POINT) + 1);
    }

    protected String currentDir() {
        return DateUtils.currentYear().toString()
                + File.separator
                + DateUtils.currentMonth().toString()
                + File.separator + DateUtils.currentDay().toString();
    }
}
