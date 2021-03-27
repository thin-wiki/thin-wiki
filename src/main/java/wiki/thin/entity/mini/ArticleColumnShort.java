package wiki.thin.entity.mini;

import lombok.Data;
import wiki.thin.constant.enums.SharableEnum;

import java.io.Serializable;

/**
 * @author Beldon
 */
@Data
public class ArticleColumnShort implements Serializable {
    private Long id;
    private String title;
    private SharableEnum sharable;
}
