package wiki.thin.entity.mini;

import lombok.Data;
import wiki.thin.constant.enums.SharableEnum;

import java.io.Serializable;

/**
 * @author Beldon
 */
@Data
public class ArticleList implements Serializable {
    private Long id;
    private String title;
    private Long parentId;
    private SharableEnum sharable;
}
