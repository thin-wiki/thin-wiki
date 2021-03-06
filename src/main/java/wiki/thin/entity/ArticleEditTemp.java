package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleEditTemp extends BaseEntity {
    private Long id;

    private Long articleId;

    private String title;

    private String content;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
