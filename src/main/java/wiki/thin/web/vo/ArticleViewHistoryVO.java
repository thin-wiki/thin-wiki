package wiki.thin.web.vo;

import lombok.Data;

/**
 * @author Beldon
 */
@Data
public class ArticleViewHistoryVO {
    private String title;

    private String columnPath;

    private Long articleId;
}
