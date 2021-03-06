package wiki.thin.web.vo;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Beldon
 */
@Data
public class ArticleColumnModifyVO {
    @NotEmpty(message = "path不能为空")
    private String path;

    @NotEmpty(message = "名字不能为空")
    private String title;

    private String content;
}
