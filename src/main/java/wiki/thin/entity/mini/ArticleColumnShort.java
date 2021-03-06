package wiki.thin.entity.mini;

import lombok.Data;
import wiki.thin.constant.enums.SharableEnum;

/**
 * @author Beldon
 */
@Data
public class ArticleColumnShort {
    private Long id;
    private String title;
    private SharableEnum sharable;
}
