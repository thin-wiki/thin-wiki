package wiki.thin.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wiki.thin.entity.Storage;
import wiki.thin.mapper.StorageMapper;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Beldon
 */
@Service
@Slf4j
public class DefaultStorageFileManager implements StorageFileManager{
    private final ExecutorService executorService = new ThreadPoolExecutor(5, 5,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(),
            new ThreadFactory() {
                private final AtomicInteger threadNumber = new AtomicInteger(1);

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "storage" + threadNumber.getAndIncrement());
                }
            });

    private final StorageMapper storageMapper;

    private final StorageServiceFactory storageServiceFactory;

    private final Map<Long, List<StorageService>> backupStorageMap = new ConcurrentHashMap<>();

    private StorageService mainStorageService;

    public DefaultStorageFileManager(StorageMapper storageMapper, StorageServiceFactory storageServiceFactory) {
        this.storageMapper = storageMapper;
        this.storageServiceFactory = storageServiceFactory;
    }

    @Override
    public Long store(MultipartFile file, Long targetId) throws IOException {

        final StorageService storageService = chooseStorage();
        final StoredFile storedFile = storageService.store(file, targetId);

        final byte[] fileData = file.getBytes();
        executorService.submit(() -> {
            final List<StorageService> backupServices = getBackupService(storageService.storageId());

            for (StorageService backupService : backupServices) {
                try {
                    backupService.saveFile(fileData, storedFile.getRelativePath());
                } catch (IOException e) {
                    log.error("save backup file error", e);
                }
            }

        });

        return storedFile.getFileId();
    }

    @Override
    public URI getUri(Long fileId) {
        return chooseStorage().getUri(fileId);
    }

    @Override
    public void cleanCache() {
        backupStorageMap.clear();
        mainStorageService = null;
    }

    @Override
    public void copy(StorageType sourceType, Long sourceId, StorageType targetType, Long targetId) {

    }

    private StorageService chooseStorage() {
        if (mainStorageService == null) {
            final List<Storage> mainStorages = storageMapper.findByWorkTypeAndWritable(StorageWorkType.MAIN, Boolean.TRUE);
            mainStorageService = storageServiceFactory.build(mainStorages.get(0));
        }
        return mainStorageService;
    }

    private List<StorageService> getBackupService(Long mainStorageId) {
        if (backupStorageMap.containsKey(mainStorageId)) {
            return backupStorageMap.get(mainStorageId);
        }
        final List<Storage> storages = storageMapper.findByMainStorageId(mainStorageId);
        List<StorageService> storageServices = new ArrayList<>();
        for (Storage storage : storages) {
            storageServices.add(storageServiceFactory.build(storage));
        }

        backupStorageMap.put(mainStorageId, storageServices);
        return storageServices;
    }
}
