package wiki.thin.storage;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import wiki.thin.entity.Storage;
import wiki.thin.exception.UnexpectedException;
import wiki.thin.repo.GiteeStorageAutoRepo;
import wiki.thin.repo.GithubStorageAutoRepo;
import wiki.thin.repo.LocalStorageAutoRepo;
import wiki.thin.storage.gitee.GiteeStorageService;
import wiki.thin.storage.github.GithubStorageService;
import wiki.thin.storage.local.LocalStorageService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Beldon
 */
@Service
@AllArgsConstructor
public class StorageServiceFactory {

    private final LocalStorageAutoRepo localStorageAutoRepo;
    private final GiteeStorageAutoRepo giteeStorageAutoRepo;
    private final GithubStorageAutoRepo githubStorageAutoRepo;

    private final Map<String, StorageService> storageCache = new ConcurrentHashMap<>();


    protected Mono<StorageService> buildStorageService(Storage storage) {
        final StorageType refStorageType = storage.getRefStorageType();
        if (refStorageType == null) {
            throw new UnexpectedException("storage 类型不能为空");
        }
        return buildStorageService(refStorageType, storage.getRefStorageId());
    }

    protected Mono<StorageService> buildStorageService(StorageType storageType, Long storageId) {

        final String storageKey = generateKey(storageType, storageId);
        if (storageCache.containsKey(storageKey)) {
            return Mono.just(storageCache.get(storageKey));
        }

        Mono<StorageService> storageServiceMono;

        if (storageType.equals(StorageType.LOCAL)) {
            storageServiceMono = localStorageAutoRepo.findById(storageId).map(LocalStorageService::new);
        } else if (storageType.equals(StorageType.GITEE)) {
            storageServiceMono = giteeStorageAutoRepo.findById(storageId).map(GiteeStorageService::new);
        } else if (storageType.equals(StorageType.GITHUB)) {
            storageServiceMono = githubStorageAutoRepo.findById(storageId).map(GithubStorageService::new);
        } else {
            throw new UnexpectedException("no support type");
        }

//        if (storageService == null) {
//            throw new UnexpectedException("找不到指定的 storage, type:" + storageType + ", id:" + storageId);
//        }

        return storageServiceMono.doOnNext(storageService -> {
            storageCache.put(storageKey, storageService);
        });
    }

    protected void clear() {
        storageCache.clear();
    }

    private String generateKey(StorageType storageType, Long storageId) {
        return storageType.name() + storageId;
    }
}
