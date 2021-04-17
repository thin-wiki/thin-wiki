package wiki.thin.entity.mini;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Beldon
 */
@Data
public class ArticleLastModifiedList implements Serializable {
    private Long id;
    private String title;
    private Long columnId;
    private Date lastModifiedDate;
}
