package wiki.thin.web.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Beldon
 */
@Data
public class GithubStorageModifyVO {
    @NotEmpty(message = "名称不能为空")
    private String name;

    private String description;

    @NotEmpty(message = "token不能为空")
    private String token;

    @NotEmpty(message = "owner不能为空")
    private String owner;

    @NotEmpty(message = "repo不能为空")
    private String repo;

    @NotEmpty(message = "branch不能为空")
    private String branch;

    @NotEmpty(message = "路径不能为空")
    private String basePath;
}
