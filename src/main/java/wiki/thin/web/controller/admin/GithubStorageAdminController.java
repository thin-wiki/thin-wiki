package wiki.thin.web.controller.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import wiki.thin.entity.GithubStorage;
import wiki.thin.mapper.GithubStorageMapper;
import wiki.thin.web.vo.GithubStorageModifyVO;
import wiki.thin.web.vo.GithubStorageVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/admin/storage/github")
public class GithubStorageAdminController {
    private final GithubStorageMapper githubStorageMapper;

    public GithubStorageAdminController(GithubStorageMapper githubStorageMapper) {
        this.githubStorageMapper = githubStorageMapper;
    }

    @PostMapping
    public ResponseVO saveStorage(@Valid @RequestBody GithubStorageModifyVO giteeStorageModifyVO) {

        var githubStorage = new GithubStorage();
        githubStorage.setName(giteeStorageModifyVO.getName());
        githubStorage.setDescription(giteeStorageModifyVO.getDescription());
        githubStorage.setToken(giteeStorageModifyVO.getToken());
        githubStorage.setOwner(giteeStorageModifyVO.getOwner());
        githubStorage.setRepo(giteeStorageModifyVO.getRepo());
        githubStorage.setBranch(giteeStorageModifyVO.getBranch());
        githubStorage.setBasePath(giteeStorageModifyVO.getBasePath());

        githubStorageMapper.insert(githubStorage);

        return ResponseVO.successWithData(githubStorage.getId());
    }

    @PutMapping("/{storageId}")
    public ResponseVO updateStorage(@PathVariable Long storageId, @Valid @RequestBody GithubStorageModifyVO giteeStorageModifyVO) {
        final Optional<GithubStorage> githubStorageOptional = githubStorageMapper.findById(storageId);
        if (githubStorageOptional.isEmpty()) {
            return ResponseVO.error("找不到指定记录");
        }
        final GithubStorage githubStorage = githubStorageOptional.get();
        githubStorage.setName(giteeStorageModifyVO.getName());
        githubStorage.setDescription(giteeStorageModifyVO.getDescription());
        githubStorage.setToken(giteeStorageModifyVO.getToken());
        githubStorage.setOwner(giteeStorageModifyVO.getOwner());
        githubStorage.setRepo(giteeStorageModifyVO.getRepo());
        githubStorage.setBranch(giteeStorageModifyVO.getBranch());
        githubStorage.setBasePath(giteeStorageModifyVO.getBasePath());

        githubStorageMapper.updateById(githubStorage);
        return ResponseVO.successWithData(githubStorage.getId());
    }

    @DeleteMapping("/{storageId}")
    public ResponseVO deleteStorage(@PathVariable Long storageId) {
        githubStorageMapper.deleteById(storageId);
        return ResponseVO.success();
    }

    @GetMapping
    public ResponseVO<List<GithubStorageVO>> listStorage() {
        List<GithubStorageVO> githubStorageVOs = new ArrayList<>();

        final List<GithubStorage> storages = githubStorageMapper.findAll();
        for (GithubStorage storage : storages) {
            GithubStorageVO githubStorageVO = new GithubStorageVO();
            BeanUtils.copyProperties(storage, githubStorageVO);
            githubStorageVOs.add(githubStorageVO);
        }
        return ResponseVO.successWithData(githubStorageVOs);
    }
}
