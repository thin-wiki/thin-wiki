package wiki.thin.search.bean;

import lombok.Data;
import wiki.thin.constant.enums.SharableEnum;

import java.util.Date;

/**
 * @author Beldon
 */
@Data
public class ArticleSearch {
    private Long id;
    private String title;
    private String content;
    private Long columnId;
    private SharableEnum selfSharable;
    private SharableEnum finalSharable;
    private Date lastModifiedDate;
}
