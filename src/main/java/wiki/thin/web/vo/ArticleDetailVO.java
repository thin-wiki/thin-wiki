package wiki.thin.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Beldon
 */
@Data
public class ArticleDetailVO implements Serializable {
    private Long id;
    private String title;
    private Long columnId;
    private String columnTitle;
    private Date createdDate;
    private Date lastModifiedDate;
}
