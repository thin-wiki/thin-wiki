package wiki.thin.web.controller.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import wiki.thin.entity.GiteeStorage;
import wiki.thin.mapper.GiteeStorageMapper;
import wiki.thin.web.vo.GiteeStorageModifyVO;
import wiki.thin.web.vo.GiteeStorageVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/admin/storage/gitee")
public class GiteeStorageAdminController {
    private final GiteeStorageMapper giteeStorageMapper;

    public GiteeStorageAdminController(GiteeStorageMapper giteeStorageMapper) {
        this.giteeStorageMapper = giteeStorageMapper;
    }

    @PostMapping
    public ResponseVO saveStorage(@Valid @RequestBody GiteeStorageModifyVO giteeStorageModifyVO) {

        var giteeStorage = new GiteeStorage();
        giteeStorage.setName(giteeStorageModifyVO.getName());
        giteeStorage.setDescription(giteeStorageModifyVO.getDescription());
        giteeStorage.setToken(giteeStorageModifyVO.getToken());
        giteeStorage.setOwner(giteeStorageModifyVO.getOwner());
        giteeStorage.setRepo(giteeStorageModifyVO.getRepo());
        giteeStorage.setBranch(giteeStorageModifyVO.getBranch());
        giteeStorage.setBasePath(giteeStorageModifyVO.getBasePath());

        giteeStorageMapper.insertSelective(giteeStorage);

        return ResponseVO.successWithData(giteeStorage.getId());
    }

    @PutMapping("/{storageId}")
    public ResponseVO updateStorage(@PathVariable Long storageId, @Valid @RequestBody GiteeStorageModifyVO giteeStorageModifyVO) {
        final Optional<GiteeStorage> giteeStorageOptional = giteeStorageMapper.findById(storageId);
        if (giteeStorageOptional.isEmpty()) {
            return ResponseVO.error("找不到指定记录");
        }
        final var giteeStorage = giteeStorageOptional.get();
        giteeStorage.setName(giteeStorageModifyVO.getName());
        giteeStorage.setDescription(giteeStorageModifyVO.getDescription());
        giteeStorage.setToken(giteeStorageModifyVO.getToken());
        giteeStorage.setOwner(giteeStorageModifyVO.getOwner());
        giteeStorage.setRepo(giteeStorageModifyVO.getRepo());
        giteeStorage.setBranch(giteeStorageModifyVO.getBranch());
        giteeStorage.setBasePath(giteeStorageModifyVO.getBasePath());

        giteeStorageMapper.updateByIdSelective(giteeStorage);
        return ResponseVO.successWithData(giteeStorage.getId());
    }

    @DeleteMapping("/{storageId}")
    public ResponseVO deleteStorage(@PathVariable Long storageId) {

        giteeStorageMapper.delete(storageId);

        return ResponseVO.success();
    }

    @GetMapping
    public ResponseVO<List<GiteeStorageVO>> listStorage() {
        List<GiteeStorageVO> giteeStorageVOs = new ArrayList<>();

        final List<GiteeStorage> storages = giteeStorageMapper.findAll();
        for (GiteeStorage storage : storages) {
            var giteeStorageVO = new GiteeStorageVO();
            BeanUtils.copyProperties(storage, giteeStorageVO);
            giteeStorageVOs.add(giteeStorageVO);
        }
        return ResponseVO.successWithData(giteeStorageVOs);
    }
}
