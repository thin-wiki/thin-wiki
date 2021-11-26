package wiki.thin.module.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wiki.thin.common.util.FileUtils;
import wiki.thin.entity.Storage;
import wiki.thin.entity.StorageFile;
import wiki.thin.exception.UnexpectedException;
import wiki.thin.mapper.StorageFileMapper;
import wiki.thin.mapper.StorageMapper;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * @author Beldon
 */
@Service
@Slf4j
public class DefaultStorageFileManager implements StorageFileManager {
    private final StorageMapper storageMapper;
    private final StorageServiceFactory storageServiceFactory;
    private final StorageFileMapper storageFileMapper;

    public DefaultStorageFileManager(StorageMapper storageMapper, StorageServiceFactory storageServiceFactory,
                                     StorageFileMapper storageFileMapper) {
        this.storageMapper = storageMapper;
        this.storageServiceFactory = storageServiceFactory;
        this.storageFileMapper = storageFileMapper;
    }

    @Override
    public Long store(MultipartFile file, Long targetId) throws IOException {

        final Storage storage = chooseStorage();

        final StorageService storageService = storageServiceFactory.buildStorageService(storage);

        final String relativePath = storageService.getRelativePath(StorageFileType.IMAGE, file.getOriginalFilename());
        storageService.saveFile(file.getBytes(), relativePath);

        final Long fileId = saveFile(file, relativePath, targetId, storage.getId());

        saveBackup(storage.getId(), file.getBytes(), relativePath);

        return fileId;
    }

    @Override
    public URI getUri(Long fileId) {

        final StorageFile storageFile = storageFileMapper.selectById(fileId);
        if (storageFile == null) {
            return null;
        }

        final Storage storage = chooseStorage();
        final StorageService storageService = storageServiceFactory.buildStorageService(storage);

        return storageService.getUri(storageFile.getRelativePath());
    }

    @Override
    public void cleanCache() {
        storageServiceFactory.clear();
    }

    @Override
    public void copy(Storage storage, StorageType targetType, Long targetId) {
        final List<StorageFile> storageFiles = storageFileMapper.findAllByStorageId(storage.getId());
        final StorageService storageService = storageServiceFactory.buildStorageService(storage);

        final StorageService targetStorageService = storageServiceFactory.buildStorageService(targetType, targetId);

        for (StorageFile file : storageFiles) {
            try {
                log.debug("copy file:{}", file.getId());
                targetStorageService.saveFile(FileUtils.readAllByte(storageService.getUri(file.getRelativePath())),
                        file.getRelativePath());
            } catch (Exception e) {
                log.error("copy file error,{}", file.getId(), e);
            }
        }
        log.debug("复制完成");
    }

    private Long saveFile(MultipartFile file, String relativePath, Long targetId, Long storageId) {

        final String originalFilename = file.getOriginalFilename();

        StorageFile storageFile = new StorageFile();
        storageFile.setTargetId(targetId);
        storageFile.setOriginalFileName(originalFilename);
        storageFile.setSuffix(FileUtils.getSuffix(originalFilename));
        storageFile.setFileSize(file.getSize());
        storageFile.setContentType(file.getContentType());
        storageFile.setRelativePath(relativePath);
        storageFile.setStorageId(storageId);
        storageFileMapper.insert(storageFile);

        return storageFile.getId();
    }

    /**
     * 保存backup 文件
     *
     * @param mainStorageId mainStorageId
     * @param fileData      fileData
     * @param relativePath  relativePath
     */
    private void saveBackup(Long mainStorageId, byte[] fileData, String relativePath) {
        StorageExecutorPool.submit(() -> {

            final List<Storage> storages = storageMapper.findByMainStorageId(mainStorageId);
            for (Storage storage : storages) {
                try {
                    storageServiceFactory.buildStorageService(storage).saveFile(fileData, relativePath);
                } catch (Exception e) {
                    log.error("save backup file error", e);
                }
            }

        });
    }

    /**
     * 选择存储
     *
     * @return Storage
     */
    private Storage chooseStorage() {
        final List<Storage> mainStorages = storageMapper.findByWorkTypeAndWritable(StorageWorkType.MAIN, Boolean.TRUE);
        if (mainStorages.isEmpty()) {
            throw new UnexpectedException("主存储不存在");
        }
        return mainStorages.get(0);
    }

}
