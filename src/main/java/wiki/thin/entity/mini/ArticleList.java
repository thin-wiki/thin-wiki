package wiki.thin.entity.mini;

import lombok.Data;
import wiki.thin.constant.enums.SharableEnum;

/**
 * @author Beldon
 */
@Data
public class ArticleList {
    private Long id;
    private String title;
    private Long parentId;
    private SharableEnum sharable;
}
