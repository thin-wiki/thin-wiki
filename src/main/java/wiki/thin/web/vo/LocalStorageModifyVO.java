package wiki.thin.web.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Beldon
 */
@Data
public class LocalStorageModifyVO {
    @NotEmpty(message = "名字不能为空")
    private String name;

    private String description;

    @NotEmpty(message = "路径不能为空")
    private String basePath;
}
