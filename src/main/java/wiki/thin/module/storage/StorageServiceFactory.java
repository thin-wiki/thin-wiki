package wiki.thin.module.storage;

import org.springframework.stereotype.Service;
import wiki.thin.entity.GiteeStorage;
import wiki.thin.entity.GithubStorage;
import wiki.thin.entity.LocalStorage;
import wiki.thin.entity.Storage;
import wiki.thin.exception.UnexpectedException;
import wiki.thin.mapper.GiteeStorageMapper;
import wiki.thin.mapper.GithubStorageMapper;
import wiki.thin.mapper.LocalStorageMapper;
import wiki.thin.module.storage.gitee.GiteeStorageService;
import wiki.thin.module.storage.github.GithubStorageService;
import wiki.thin.module.storage.local.LocalStorageService;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Beldon
 */
@Service
public class StorageServiceFactory {

    private final LocalStorageMapper localStorageMapper;
    private final GiteeStorageMapper giteeStorageMapper;
    private final GithubStorageMapper githubStorageMapper;

    private final Map<String, StorageService> storageCache = new ConcurrentHashMap<>();

    public StorageServiceFactory(LocalStorageMapper localStorageMapper,
                                 GiteeStorageMapper giteeStorageMapper,
                                 GithubStorageMapper githubStorageMapper) {
        this.localStorageMapper = localStorageMapper;
        this.giteeStorageMapper = giteeStorageMapper;
        this.githubStorageMapper = githubStorageMapper;
    }

    protected StorageService buildStorageService(Storage storage) {
        final StorageType refStorageType = storage.getRefStorageType();
        if (refStorageType == null) {
            throw new UnexpectedException("storage 类型不能为空");
        }
        return buildStorageService(refStorageType, storage.getRefStorageId());
    }

    protected StorageService buildStorageService(StorageType storageType, Long storageId) {

        final String storageKey = generateKey(storageType, storageId);
        if (storageCache.containsKey(storageKey)) {
            return storageCache.get(storageKey);
        }

        StorageService storageService = null;

        if (storageType.equals(StorageType.LOCAL)) {
            final Optional<LocalStorage> localStorageOptional = localStorageMapper.findById(storageId);
            if (localStorageOptional.isPresent()) {
                storageService = new LocalStorageService(localStorageOptional.get());
            }
        } else if (storageType.equals(StorageType.GITEE)) {
            final Optional<GiteeStorage> giteeStorageOptional = giteeStorageMapper.findById(storageId);
            if (giteeStorageOptional.isPresent()) {
                storageService = new GiteeStorageService(giteeStorageOptional.get());
            }
        } else if (storageType.equals(StorageType.GITHUB)) {
            final Optional<GithubStorage> githubStorageOptional = githubStorageMapper.findById(storageId);
            if (githubStorageOptional.isPresent()) {
                storageService = new GithubStorageService(githubStorageOptional.get());
            }
        } else {
            throw new UnexpectedException("no support type");
        }

        if (storageService == null) {
            throw new UnexpectedException("找不到指定的 storage, type:" + storageType + ", id:" + storageId);
        }

        storageCache.put(storageKey, storageService);

        return storageService;
    }

    protected void clear() {
        storageCache.clear();
    }

    private String generateKey(StorageType storageType, Long storageId) {
        return storageType.name() + storageId;
    }
}
