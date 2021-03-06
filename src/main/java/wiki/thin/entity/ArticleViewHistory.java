package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleViewHistory extends BaseEntity {

    private Long articleId;

    private Long createdBy;

    private Date lastModifiedDate;
}
