package wiki.thin.web.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import wiki.thin.entity.Storage;
import wiki.thin.repo.GiteeStorageAutoRepo;
import wiki.thin.repo.GithubStorageAutoRepo;
import wiki.thin.repo.LocalStorageAutoRepo;
import wiki.thin.repo.StorageAutoRepo;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.web.vo.ResponseVO;
import wiki.thin.web.vo.StorageBindVO;
import wiki.thin.web.vo.StorageModifyVO;

import javax.validation.Valid;
import java.util.function.Function;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/admin/storage")
@NeedAuth
@AllArgsConstructor
public class StorageAdminController {
    private final StorageAutoRepo storageAutoRepo;
    private final LocalStorageAutoRepo localStorageAutoRepo;
    private final GiteeStorageAutoRepo giteeStorageAutoRepo;
    private final GithubStorageAutoRepo githubStorageAutoRepo;

    @PostMapping
    public Mono<ResponseVO> saveStorage(@Valid @RequestBody StorageModifyVO storageModifyVO) {
        var storage = new Storage();
        storage.setName(storageModifyVO.getName());
        storage.setDescription(storageModifyVO.getDescription());
        storage.setWorkType(storageModifyVO.getWorkType());
        storage.setMainStorageId(storageModifyVO.getMainStorageId());
        storage.setWritable(storageModifyVO.getWritable());
        return storageAutoRepo.save(storage)
                .thenReturn(ResponseVO.success());
    }

    @PutMapping("/{storageId}")
    public Mono<ResponseVO> updateStorage(@PathVariable Long storageId, @Valid @RequestBody StorageModifyVO storageModifyVO) {
        return storageAutoRepo.findById(storageId)
                .flatMap(storage -> {
                    storage.setName(storageModifyVO.getName());
                    storage.setDescription(storageModifyVO.getDescription());
                    storage.setWorkType(storageModifyVO.getWorkType());
                    storage.setMainStorageId(storageModifyVO.getMainStorageId());
                    storage.setWritable(storageModifyVO.getWritable());
                    return storageAutoRepo.save(storage).thenReturn(ResponseVO.success());
                }).defaultIfEmpty(ResponseVO.error("找不到指定记录"));

    }

    @PutMapping("/{storageId}/bind")
    public Mono<ResponseVO> bindStorage(@PathVariable Long storageId, @Valid @RequestBody StorageBindVO bindVO) {

        return storageAutoRepo.findById(storageId)
                .flatMap(new Function<Storage, Mono<ResponseVO>>() {
                    @Override
                    public Mono<ResponseVO> apply(Storage storage) {
                        storage.setRefStorageType(bindVO.getRefStorageType());
                        storage.setRefStorageId(bindVO.getRefStorageId());
                        return storageAutoRepo.save(storage)
                                .doOnNext(storage1 -> {
//                                        storageFileManager.cleanCache();
                                })
                                .thenReturn(ResponseVO.success());
                    }
                }).defaultIfEmpty(ResponseVO.error("找不到指定记录"));

    }

    @DeleteMapping("/{storageId}")
    public Mono<ResponseVO> deleteStorage(@PathVariable Long storageId) {
        return storageAutoRepo.deleteById(storageId)
                .thenReturn(ResponseVO.success());
    }

    @PutMapping("/{storageId}/copy")
    public Mono<ResponseVO> copyFile(@PathVariable Long storageId, @Valid @RequestBody StorageBindVO bindVO) {

        return storageAutoRepo.findById(storageId)
                .doOnNext(storage -> {
//                        storageFileManager.copy(storage, bindVO.getRefStorageType(), bindVO.getRefStorageId());
                }).thenReturn(ResponseVO.success())
                .defaultIfEmpty(ResponseVO.error("找不到指定记录"));

    }

//    @GetMapping
//    public ResponseVO<List<StorageVO>> list(@RequestParam(required = false) StorageWorkType workType) {
//        final List<Storage> storages = storageMapper.findAll();
//        final Map<Long, Storage> storageMap = storages.stream().collect(Collectors.toMap(Storage::getId, s -> s));
//
//        List<StorageVO> storageVos = new ArrayList<>();
//
//        for (Storage storage : storages) {
//            if (workType != null && !workType.equals(storage.getWorkType())) {
//                continue;
//            }
//            var storageVo = new StorageVO();
//            storageVo.setId(storage.getId());
//            storageVo.setName(storage.getName());
//            storageVo.setDescription(storage.getDescription());
//            storageVo.setWorkType(storage.getWorkType());
//            storageVo.setRefStorageType(storage.getRefStorageType());
//            storageVo.setRefStorageId(storage.getRefStorageId());
//            storageVo.setRefStorageName(getRefStorageName(storage.getRefStorageType(), storage.getRefStorageId()));
//            storageVo.setMainStorageId(storage.getMainStorageId());
//            if (StorageWorkType.BACKUP.equals(storage.getWorkType())
//                    && storage.getMainStorageId() != null && storageMap.containsKey(storage.getMainStorageId())) {
//                storageVo.setMainStorageName(storageMap.get(storage.getMainStorageId()).getName());
//            }
//            storageVo.setWritable(storage.getWritable());
//            storageVo.setCreatedDate(storage.getCreatedDate());
//            storageVo.setLastModifiedDate(storage.getLastModifiedDate());
//            storageVos.add(storageVo);
//        }
//
//        return ResponseVO.successWithData(storageVos);
//    }
//
//
//    private String getRefStorageName(StorageType refStorageType, Long refStorageId) {
//
//        if (refStorageType == null || refStorageId == null) {
//            return null;
//        }
//
//        switch (refStorageType) {
//            case GITEE:
//                return giteeStorageMapper.findById(refStorageId).map(GiteeStorage::getName).orElse(null);
//            case LOCAL:
//                return localStorageMapper.findById(refStorageId).map(LocalStorage::getName).orElse(null);
//            case GITHUB:
//                return githubStorageMapper.findById(refStorageId).map(GithubStorage::getName).orElse(null);
//            default:
//                throw new RuntimeException("no support type");
//        }
//    }
}
