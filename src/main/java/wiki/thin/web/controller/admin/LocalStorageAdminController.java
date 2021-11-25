package wiki.thin.web.controller.admin;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import wiki.thin.entity.LocalStorage;
import wiki.thin.mapper.LocalStorageMapper;
import wiki.thin.web.vo.LocalStorageModifyVO;
import wiki.thin.web.vo.LocalStorageVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/admin/storage/local")
public class LocalStorageAdminController {
    private final LocalStorageMapper localStorageMapper;

    public LocalStorageAdminController(LocalStorageMapper localStorageMapper) {
        this.localStorageMapper = localStorageMapper;
    }

    @PostMapping
    public ResponseVO saveStorage(@Valid @RequestBody LocalStorageModifyVO localStorageModifyVO) {

        LocalStorage localStorage = new LocalStorage();
        localStorage.setName(localStorageModifyVO.getName());
        localStorage.setDescription(localStorageModifyVO.getDescription());
        localStorage.setBasePath(localStorageModifyVO.getBasePath());

        localStorageMapper.insertSelective(localStorage);

        return ResponseVO.successWithData(localStorage.getId());
    }

    @PutMapping("/{storageId}")
    public ResponseVO updateStorage(@PathVariable Long storageId, @Valid @RequestBody LocalStorageModifyVO localStorageModifyVO) {
        final Optional<LocalStorage> localStorageOptional = localStorageMapper.findById(storageId);
        if (localStorageOptional.isEmpty()) {
            return ResponseVO.error("找不到指定记录");
        }
        final LocalStorage localStorage = localStorageOptional.get();
        localStorage.setName(localStorageModifyVO.getName());
        localStorage.setDescription(localStorageModifyVO.getDescription());
        localStorage.setBasePath(localStorageModifyVO.getBasePath());

        localStorageMapper.updateByIdSelective(localStorage);
        return ResponseVO.successWithData(localStorage.getId());
    }

    @DeleteMapping("/{storageId}")
    public ResponseVO deleteStorage(@PathVariable Long storageId) {

        localStorageMapper.delete(storageId);
        return ResponseVO.success();
    }

    @GetMapping
    public ResponseVO<List<LocalStorageVO>> listStorage() {
        List<LocalStorageVO> localStorageVoList = new ArrayList<>();

        final List<LocalStorage> storages = localStorageMapper.findAll();
        for (LocalStorage storage : storages) {
            LocalStorageVO localStorageVO = new LocalStorageVO();
            BeanUtils.copyProperties(storage, localStorageVO);
            localStorageVoList.add(localStorageVO);
        }
        return ResponseVO.successWithData(localStorageVoList);
    }

}
