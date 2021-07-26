package wiki.thin.web.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import wiki.thin.entity.LocalStorage;
import wiki.thin.repo.LocalStorageAutoRepo;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.web.vo.LocalStorageModifyVO;
import wiki.thin.web.vo.LocalStorageVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/admin/storage/local")
@NeedAuth
@AllArgsConstructor
public class LocalStorageAdminController {

    private final LocalStorageAutoRepo localStorageAutoRepo;

    @PostMapping
    public Mono<ResponseVO> saveStorage(@Valid @RequestBody LocalStorageModifyVO localStorageModifyVO) {

        LocalStorage localStorage = new LocalStorage();
        localStorage.setName(localStorageModifyVO.getName());
        localStorage.setDescription(localStorageModifyVO.getDescription());
        localStorage.setBasePath(localStorageModifyVO.getBasePath());

        return localStorageAutoRepo.save(localStorage).thenReturn(ResponseVO.success());
    }

    @PutMapping("/{storageId}")
    public Mono<ResponseVO> updateStorage(@PathVariable Long storageId, @Valid @RequestBody LocalStorageModifyVO localStorageModifyVO) {

        return localStorageAutoRepo.findById(storageId)
                .flatMap(localStorage -> {
                    localStorage.setName(localStorageModifyVO.getName());
                    localStorage.setDescription(localStorageModifyVO.getDescription());
                    localStorage.setBasePath(localStorageModifyVO.getBasePath());
                    return localStorageAutoRepo.save(localStorage)
                            .thenReturn(ResponseVO.success());
                }).defaultIfEmpty(ResponseVO.error("找不到指定记录"));
    }

    @DeleteMapping("/{storageId}")
    public Mono<ResponseVO> deleteStorage(@PathVariable Long storageId) {
        return localStorageAutoRepo.deleteById(storageId)
                .thenReturn(ResponseVO.success());
    }

    @GetMapping
    public Mono<ResponseVO<List<LocalStorageVO>>> listStorage() {
        final Sort sort = Sort.by(Sort.Direction.ASC, "lastModifiedDate");
        return localStorageAutoRepo.findAll(sort)
                .map(localStorage -> {
                    LocalStorageVO localStorageVO = new LocalStorageVO();
                    BeanUtils.copyProperties(localStorage, localStorageVO);
                    return localStorageVO;
                }).collectList()
                .map(ResponseVO::successWithData);
    }

}
