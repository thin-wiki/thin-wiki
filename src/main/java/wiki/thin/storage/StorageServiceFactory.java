package wiki.thin.storage;

import org.springframework.stereotype.Service;
import wiki.thin.entity.GiteeStorage;
import wiki.thin.entity.LocalStorage;
import wiki.thin.entity.Storage;
import wiki.thin.mapper.GiteeStorageMapper;
import wiki.thin.mapper.LocalStorageMapper;
import wiki.thin.mapper.StorageFileMapper;
import wiki.thin.storage.gitee.GiteeStorageService;
import wiki.thin.storage.local.LocalStorageService;

import java.util.Optional;

/**
 * @author Beldon
 */
@Service
public class StorageServiceFactory {

    private final LocalStorageMapper localStorageMapper;

    private final GiteeStorageMapper giteeStorageMapper;

    private final StorageFileMapper storageFileMapper;

    public StorageServiceFactory(LocalStorageMapper localStorageMapper, GiteeStorageMapper giteeStorageMapper,
                                 StorageFileMapper storageFileMapper) {
        this.localStorageMapper = localStorageMapper;
        this.giteeStorageMapper = giteeStorageMapper;
        this.storageFileMapper = storageFileMapper;
    }

    public StorageService build(Storage storage) {
        final StorageType refStorageType = storage.getRefStorageType();
        if (refStorageType == null) {
            throw new RuntimeException("点不能为空");
        }
        StorageService storageService = null;
        if (refStorageType.equals(StorageType.LOCAL)) {
            final Optional<LocalStorage> localStorageOptional = localStorageMapper.findById(storage.getRefStorageId());
            if (localStorageOptional.isPresent()) {
                storageService = new LocalStorageService(storageFileMapper, storage, localStorageOptional.get());
            }
        } else if (refStorageType.equals(StorageType.GITEE)) {
            final Optional<GiteeStorage> giteeStorageOptional = giteeStorageMapper.findById(storage.getRefStorageId());
            if (giteeStorageOptional.isPresent()) {
                storageService = new GiteeStorageService(storageFileMapper, storage, giteeStorageOptional.get());
            }
        } else {
            throw new RuntimeException("no support type");
        }

        return storageService;
    }
}
