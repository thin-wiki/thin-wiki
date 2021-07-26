package wiki.thin.web.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import wiki.thin.entity.GiteeStorage;
import wiki.thin.repo.GiteeStorageAutoRepo;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.web.vo.GiteeStorageModifyVO;
import wiki.thin.web.vo.GiteeStorageVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/admin/storage/gitee")
@NeedAuth
@AllArgsConstructor
public class GiteeStorageAdminController {
    private final GiteeStorageAutoRepo giteeStorageAutoRepo;

    @PostMapping
    public Mono<ResponseVO> saveStorage(@Valid @RequestBody GiteeStorageModifyVO giteeStorageModifyVO) {

        var giteeStorage = new GiteeStorage();
        giteeStorage.setName(giteeStorageModifyVO.getName());
        giteeStorage.setDescription(giteeStorageModifyVO.getDescription());
        giteeStorage.setToken(giteeStorageModifyVO.getToken());
        giteeStorage.setOwner(giteeStorageModifyVO.getOwner());
        giteeStorage.setRepo(giteeStorageModifyVO.getRepo());
        giteeStorage.setBranch(giteeStorageModifyVO.getBranch());
        giteeStorage.setBasePath(giteeStorageModifyVO.getBasePath());

        return giteeStorageAutoRepo.save(giteeStorage).thenReturn(ResponseVO.success());
    }

    @PutMapping("/{storageId}")
    public Mono<ResponseVO> updateStorage(@PathVariable Long storageId, @Valid @RequestBody GiteeStorageModifyVO giteeStorageModifyVO) {
        return giteeStorageAutoRepo.findById(storageId)
                .flatMap(giteeStorage -> {
                    giteeStorage.setName(giteeStorageModifyVO.getName());
                    giteeStorage.setDescription(giteeStorageModifyVO.getDescription());
                    giteeStorage.setToken(giteeStorageModifyVO.getToken());
                    giteeStorage.setOwner(giteeStorageModifyVO.getOwner());
                    giteeStorage.setRepo(giteeStorageModifyVO.getRepo());
                    giteeStorage.setBranch(giteeStorageModifyVO.getBranch());
                    giteeStorage.setBasePath(giteeStorageModifyVO.getBasePath());
                    return giteeStorageAutoRepo.save(giteeStorage)
                            .thenReturn(ResponseVO.success());
                }).defaultIfEmpty(ResponseVO.error("找不到指定记录"));

    }

    @DeleteMapping("/{storageId}")
    public Mono<ResponseVO> deleteStorage(@PathVariable Long storageId) {
        return giteeStorageAutoRepo.deleteById(storageId)
                .thenReturn(ResponseVO.success());
    }

    @GetMapping
    public Mono<ResponseVO<List<GiteeStorageVO>>> listStorage() {
        final Sort sort = Sort.by(Sort.Direction.ASC, "lastModifiedDate");
        return giteeStorageAutoRepo.findAll(sort)
                .map(giteeStorage -> {
                    var giteeStorageVO = new GiteeStorageVO();
                    BeanUtils.copyProperties(giteeStorage, giteeStorageVO);
                    return giteeStorageVO;
                }).collectList()
                .map(ResponseVO::successWithData);
    }
}
