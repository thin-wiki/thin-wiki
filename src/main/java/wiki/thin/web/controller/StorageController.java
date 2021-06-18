package wiki.thin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wiki.thin.entity.GiteeStorage;
import wiki.thin.entity.GithubStorage;
import wiki.thin.entity.LocalStorage;
import wiki.thin.entity.Storage;
import wiki.thin.mapper.GiteeStorageMapper;
import wiki.thin.mapper.GithubStorageMapper;
import wiki.thin.mapper.LocalStorageMapper;
import wiki.thin.mapper.StorageMapper;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.storage.StorageType;
import wiki.thin.storage.StorageWorkType;
import wiki.thin.web.vo.StorageVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Beldon
 */
@Controller
@RequestMapping("/storage")
@Deprecated
public class StorageController extends BaseController {

    private final StorageMapper storageMapper;

    private final LocalStorageMapper localStorageMapper;

    private final GithubStorageMapper githubStorageMapper;

    private final GiteeStorageMapper giteeStorageMapper;

    public StorageController(StorageMapper storageMapper, LocalStorageMapper localStorageMapper,
                             GithubStorageMapper githubStorageMapper, GiteeStorageMapper giteeStorageMapper) {
        this.storageMapper = storageMapper;
        this.localStorageMapper = localStorageMapper;
        this.githubStorageMapper = githubStorageMapper;
        this.giteeStorageMapper = giteeStorageMapper;
    }

    @GetMapping
    @NeedAuth
    public String index(Model model) {
        final List<Storage> storages = storageMapper.findAll();
        final Map<Long, Storage> storageMap = storages.stream().collect(Collectors.toMap(Storage::getId, s -> s));

        List<StorageVO> storageVOs = new ArrayList<>();

        for (Storage storage : storages) {
            StorageVO storageVO = new StorageVO();
            storageVO.setId(storage.getId());
            storageVO.setName(storage.getName());
            storageVO.setDescription(storage.getDescription());
            storageVO.setWorkType(storage.getWorkType());
            storageVO.setRefStorageType(storage.getRefStorageType());
            storageVO.setRefStorageId(storage.getRefStorageId());
            storageVO.setRefStorageName(getRefStorageName(storage.getRefStorageType(), storage.getRefStorageId()));
            storageVO.setMainStorageId(storage.getMainStorageId());
            if (StorageWorkType.BACKUP.equals(storage.getWorkType())
                    && storage.getMainStorageId() != null && storageMap.containsKey(storage.getMainStorageId())) {
                storageVO.setMainStorageName(storageMap.get(storage.getMainStorageId()).getName());
            }
            storageVO.setWritable(storage.getWritable());
            storageVO.setCreatedBy(storage.getCreatedBy());
            storageVO.setCreatedDate(storage.getCreatedDate());
            storageVO.setLastModifiedBy(storage.getLastModifiedBy());
            storageVO.setLastModifiedDate(storage.getLastModifiedDate());
            storageVOs.add(storageVO);
        }
        final List<StorageVO> mainStorageVO = storageVOs.stream()
                .filter(s -> StorageWorkType.MAIN.equals(s.getWorkType())).collect(Collectors.toList());

        final List<LocalStorage> localStorages = localStorageMapper.findAll();
        final List<GithubStorage> githubStorages = githubStorageMapper.findAll();
        final List<GiteeStorage> giteeStorages = giteeStorageMapper.findAll();
        model.addAttribute("localStorages", localStorages);
        model.addAttribute("githubStorages", githubStorages);
        model.addAttribute("giteeStorages", giteeStorages);

        model.addAttribute("storageVOS", storageVOs);
        model.addAttribute("mainStorageVO", mainStorageVO);
        return "storage/index";
    }

    @GetMapping("local")
    @NeedAuth
    public String local(Model model) {
        final List<LocalStorage> localStorages = localStorageMapper.findAll();
        model.addAttribute("localStorages", localStorages);
        return "storage/local";
    }

    @GetMapping("github")
    @NeedAuth
    public String github(Model model) {
        final List<GithubStorage> githubStorages = githubStorageMapper.findAll();
        model.addAttribute("githubStorages", githubStorages);
        return "storage/github";
    }

    @GetMapping("gitee")
    @NeedAuth
    public String gitee(Model model) {
        final List<GiteeStorage> giteeStorages = giteeStorageMapper.findAll();
        model.addAttribute("giteeStorages", giteeStorages);
        return "storage/gitee";
    }

    private String getRefStorageName(StorageType storageType, Long refStorageId) {

        if (storageType == null || refStorageId == null) {
            return null;
        }

        switch (storageType) {
            case GITEE:
                return giteeStorageMapper.findById(refStorageId).map(GiteeStorage::getName).orElse(null);
            case LOCAL:
                return localStorageMapper.findById(refStorageId).map(LocalStorage::getName).orElse(null);
            case GITHUB:
                return githubStorageMapper.findById(refStorageId).map(GithubStorage::getName).orElse(null);
            default:
                throw new RuntimeException("no support type");
        }
    }
}
