package wiki.thin.web.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import wiki.thin.entity.GithubStorage;
import wiki.thin.repo.GithubStorageAutoRepo;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.web.vo.GithubStorageModifyVO;
import wiki.thin.web.vo.GithubStorageVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/admin/storage/github")
@NeedAuth
@AllArgsConstructor
public class GithubStorageAdminController {

    private final GithubStorageAutoRepo githubStorageAutoRepo;

    @PostMapping
    public Mono<ResponseVO> saveStorage(@Valid @RequestBody GithubStorageModifyVO giteeStorageModifyVO) {

        var githubStorage = new GithubStorage();
        githubStorage.setName(giteeStorageModifyVO.getName());
        githubStorage.setDescription(giteeStorageModifyVO.getDescription());
        githubStorage.setToken(giteeStorageModifyVO.getToken());
        githubStorage.setOwner(giteeStorageModifyVO.getOwner());
        githubStorage.setRepo(giteeStorageModifyVO.getRepo());
        githubStorage.setBranch(giteeStorageModifyVO.getBranch());
        githubStorage.setBasePath(giteeStorageModifyVO.getBasePath());

        return githubStorageAutoRepo.save(githubStorage)
                .thenReturn(ResponseVO.success());
    }

    @PutMapping("/{storageId}")
    public Mono<ResponseVO> updateStorage(@PathVariable Long storageId, @Valid @RequestBody GithubStorageModifyVO giteeStorageModifyVO) {

        return githubStorageAutoRepo.findById(storageId)
                .flatMap(githubStorage -> {
                    githubStorage.setName(giteeStorageModifyVO.getName());
                    githubStorage.setDescription(giteeStorageModifyVO.getDescription());
                    githubStorage.setToken(giteeStorageModifyVO.getToken());
                    githubStorage.setOwner(giteeStorageModifyVO.getOwner());
                    githubStorage.setRepo(giteeStorageModifyVO.getRepo());
                    githubStorage.setBranch(giteeStorageModifyVO.getBranch());
                    githubStorage.setBasePath(giteeStorageModifyVO.getBasePath());
                    return githubStorageAutoRepo.save(githubStorage).thenReturn(ResponseVO.success());
                }).defaultIfEmpty(ResponseVO.error("找不到指定记录"));

    }

    @DeleteMapping("/{storageId}")
    public Mono<ResponseVO> deleteStorage(@PathVariable Long storageId) {
        return githubStorageAutoRepo.deleteById(storageId)
                .thenReturn(ResponseVO.success());
    }

    @GetMapping
    public Mono<ResponseVO<List<GithubStorage>>> listStorage() {
        final Sort sort = Sort.by(Sort.Direction.ASC, "lastModifiedDate");
        return githubStorageAutoRepo.findAll(sort)
                .map(githubStorage -> {
                    GithubStorageVO githubStorageVO = new GithubStorageVO();
                    BeanUtils.copyProperties(githubStorage, githubStorageVO);
                    return githubStorage;
                }).collectList()
                .map(ResponseVO::successWithData);

    }
}
