package wiki.thin.web.vo;

import lombok.Data;
import wiki.thin.storage.StorageWorkType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Beldon
 */
@Data
public class StorageModifyVO {

    @NotEmpty(message = "名字不能为空")
    private String name;

    private String description;

    @NotNull(message = "工作类型不能为空")
    private StorageWorkType workType;

    private Long mainStorageId;

    @NotNull(message = "可写不能为空")
    private Boolean writable;
}
