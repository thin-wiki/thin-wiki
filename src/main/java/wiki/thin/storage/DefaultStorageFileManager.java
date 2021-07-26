package wiki.thin.storage;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import wiki.thin.common.util.FileUtils;
import wiki.thin.entity.BaseEntity;
import wiki.thin.entity.Storage;
import wiki.thin.entity.StorageFile;
import wiki.thin.exception.UnexpectedException;
import wiki.thin.repo.StorageAutoRepo;
import wiki.thin.repo.StorageFileAutoRepo;

import java.io.IOException;
import java.net.URI;

/**
 * @author Beldon
 */
@Service
@Slf4j
@AllArgsConstructor
public class DefaultStorageFileManager implements StorageFileManager {
    private final StorageAutoRepo storageAutoRepo;
    private final StorageFileAutoRepo storageFileAutoRepo;
    private final StorageServiceFactory storageServiceFactory;

    @Override
    public Mono<Long> store(MultipartFile file, Long targetId) throws IOException {

        final Storage storage = chooseStorage();

        final Mono<StorageService> storageServiceMono = storageServiceFactory.buildStorageService(storage);

        return storageServiceMono.flatMap(storageService -> {
            final String relativePath = storageService.getRelativePath(StorageFileType.IMAGE, file.getOriginalFilename());
            try {
                storageService.saveFile(file.getBytes(), relativePath);
                final Mono<Long> fileId = saveFile(file, relativePath, targetId, storage.getId());
                saveBackup(storage.getId(), file.getBytes(), relativePath);
                return fileId;
            } catch (IOException e) {
                throw new UnexpectedException("save file error", e);
            }
        });

    }

    @Override
    public Mono<URI> getUri(Long fileId) {
        return storageFileAutoRepo.findById(fileId).flatMap(
                storageFile -> storageServiceFactory.buildStorageService(chooseStorage())
                        .map(storageService -> storageService.getUri(storageFile.getRelativePath()))
        );
    }

    @Override
    public void cleanCache() {
        storageServiceFactory.clear();
    }

    @Override
    public void copy(Storage storage, StorageType targetType, Long targetId) {

//        final List<StorageFile> storageFiles = storageFileMapper.findAllByStorageId(storage.getId());
//        final StorageService storageService = storageServiceFactory.buildStorageService(storage);
//
//        final StorageService targetStorageService = storageServiceFactory.buildStorageService(targetType, targetId);
//
//        for (StorageFile file : storageFiles) {
//            try {
//                log.debug("copy file:{}", file.getId());
//                targetStorageService.saveFile(FileUtils.readAllByte(storageService.getUri(file.getRelativePath())),
//                        file.getRelativePath());
//            } catch (Exception e) {
//                log.error("copy file error,{}", file.getId(), e);
//            }
//        }
//        log.debug("复制完成");
    }

    private Mono<Long> saveFile(MultipartFile file, String relativePath, Long targetId, Long storageId) {

        final String originalFilename = file.getOriginalFilename();

        StorageFile storageFile = new StorageFile();
        storageFile.setTargetId(targetId);
        storageFile.setOriginalFileName(originalFilename);
        storageFile.setSuffix(FileUtils.getSuffix(originalFilename));
        storageFile.setFileSize(file.getSize());
        storageFile.setContentType(file.getContentType());
        storageFile.setRelativePath(relativePath);
        storageFile.setStorageId(storageId);

        return storageFileAutoRepo.save(storageFile).map(BaseEntity::getId);
    }

    /**
     * 保存backup 文件
     *
     * @param mainStorageId mainStorageId
     * @param fileData      fileData
     * @param relativePath  relativePath
     */
    private void saveBackup(Long mainStorageId, byte[] fileData, String relativePath) {
//        StorageExecutorPool.submit(() -> {
//
//            final List<Storage> storages = storageMapper.findByMainStorageId(mainStorageId);
//            for (Storage storage : storages) {
//                try {
//                    storageServiceFactory.buildStorageService(storage).saveFile(fileData, relativePath);
//                } catch (Exception e) {
//                    log.error("save backup file error", e);
//                }
//            }
//
//        });
    }

    /**
     * 选择存储
     *
     * @return Storage
     */
    private Storage chooseStorage() {
//        final List<Storage> mainStorages = storageMapper.findByWorkTypeAndWritable(StorageWorkType.MAIN, Boolean.TRUE);
//        if (mainStorages.isEmpty()) {
//            throw new UnexpectedException("主存储不存在");
//        }
//        return mainStorages.get(0);
        return null;
    }

}
