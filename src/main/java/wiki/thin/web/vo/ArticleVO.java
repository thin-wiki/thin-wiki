package wiki.thin.web.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Beldon
 */
@Data
public class ArticleVO {
    /**
     * column 和 parentId 不能同时为空
     */
    @NotEmpty(message = "栏目不能为空")
    private String columnPath;

    private Long parentId;

    @NotEmpty(message = "标题不能为空")
    private String title;

    @NotEmpty(message = "内容不能为空")
    private String content;
}
