package wiki.thin.entity.mini;

import lombok.Data;
import wiki.thin.constant.enums.SharableEnum;

/**
 * @author Beldon
 */
@Data
public class ArticleColumnList {
    private Long id;
    private String path;
    private String title;
    private SharableEnum sharable;
}
