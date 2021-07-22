//package wiki.thin.web.controller.admin;
//
//import org.springframework.web.bind.annotation.*;
//import wiki.thin.entity.GiteeStorage;
//import wiki.thin.entity.GithubStorage;
//import wiki.thin.entity.LocalStorage;
//import wiki.thin.entity.Storage;
//import wiki.thin.mapper.GiteeStorageMapper;
//import wiki.thin.mapper.GithubStorageMapper;
//import wiki.thin.mapper.LocalStorageMapper;
//import wiki.thin.mapper.StorageMapper;
//import wiki.thin.security.annotation.NeedAuth;
//import wiki.thin.storage.StorageFileManager;
//import wiki.thin.storage.StorageType;
//import wiki.thin.storage.StorageWorkType;
//import wiki.thin.web.vo.ResponseVO;
//import wiki.thin.web.vo.StorageBindVO;
//import wiki.thin.web.vo.StorageModifyVO;
//import wiki.thin.web.vo.StorageVO;
//
//import javax.validation.Valid;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
///**
// * @author Beldon
// */
//@RestController
//@RequestMapping("/api/admin/storage")
//@NeedAuth
//public class StorageAdminController {
//    private final StorageMapper storageMapper;
//    private final LocalStorageMapper localStorageMapper;
//    private final GiteeStorageMapper giteeStorageMapper;
//    private final GithubStorageMapper githubStorageMapper;
//    private final StorageFileManager storageFileManager;
//
//    public StorageAdminController(StorageMapper storageMapper, LocalStorageMapper localStorageMapper,
//                                  GiteeStorageMapper giteeStorageMapper, GithubStorageMapper githubStorageMapper,
//                                  StorageFileManager storageFileManager) {
//        this.storageMapper = storageMapper;
//        this.localStorageMapper = localStorageMapper;
//        this.giteeStorageMapper = giteeStorageMapper;
//        this.githubStorageMapper = githubStorageMapper;
//        this.storageFileManager = storageFileManager;
//    }
//
//    @PostMapping
//    public ResponseVO<Long> saveStorage(@Valid @RequestBody StorageModifyVO storageModifyVO) {
//        var storage = new Storage();
//        storage.setName(storageModifyVO.getName());
//        storage.setDescription(storageModifyVO.getDescription());
//        storage.setWorkType(storageModifyVO.getWorkType());
//        storage.setMainStorageId(storageModifyVO.getMainStorageId());
//        storage.setWritable(storageModifyVO.getWritable());
//        storageMapper.insertSelective(storage);
//        return ResponseVO.successWithData(storage.getId());
//    }
//
//    @PutMapping("/{storageId}")
//    public ResponseVO updateStorage(@PathVariable Long storageId, @Valid @RequestBody StorageModifyVO storageModifyVO) {
//        final Optional<Storage> storageOptional = storageMapper.findById(storageId);
//        if (storageOptional.isEmpty()) {
//            return ResponseVO.error("找不到指定记录");
//        }
//        var storage = storageOptional.get();
//        storage.setName(storageModifyVO.getName());
//        storage.setDescription(storageModifyVO.getDescription());
//        storage.setWorkType(storageModifyVO.getWorkType());
//        storage.setMainStorageId(storageModifyVO.getMainStorageId());
//        storage.setWritable(storageModifyVO.getWritable());
//        storageMapper.updateByIdSelective(storage);
//        return ResponseVO.success();
//    }
//
//    @PutMapping("/{storageId}/bind")
//    public ResponseVO bindStorage(@PathVariable Long storageId, @Valid @RequestBody StorageBindVO bindVO) {
//        final Optional<Storage> storageOptional = storageMapper.findById(storageId);
//        if (storageOptional.isEmpty()) {
//            return ResponseVO.error("找不到指定记录");
//        }
//        var storage = storageOptional.get();
//        storage.setRefStorageType(bindVO.getRefStorageType());
//        storage.setRefStorageId(bindVO.getRefStorageId());
//        storageMapper.updateByIdSelective(storage);
//
//        storageFileManager.cleanCache();
//
//        return ResponseVO.success();
//    }
//
//    @DeleteMapping("/{storageId}")
//    public ResponseVO deleteStorage(@PathVariable Long storageId) {
//        storageMapper.delete(storageId);
//        return ResponseVO.success();
//    }
//
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
//    @PutMapping("/{storageId}/copy")
//    public ResponseVO copyFile(@PathVariable Long storageId, @Valid @RequestBody StorageBindVO bindVO) {
//        final Optional<Storage> storageOptional = storageMapper.findById(storageId);
//        if (storageOptional.isEmpty()) {
//            return ResponseVO.error("找不到指定记录");
//        }
//        var storage = storageOptional.get();
//        storageFileManager.copy(storage, bindVO.getRefStorageType(), bindVO.getRefStorageId());
//
//        return ResponseVO.success();
//    }
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
//}
