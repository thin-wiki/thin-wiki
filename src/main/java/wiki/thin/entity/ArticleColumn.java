package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wiki.thin.constant.enums.SharableEnum;

import java.util.Date;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ArticleColumn extends BaseEntity {

    private String path;
    private String title;
    private String content;
    private SharableEnum sharable;
    private Long createdBy;
    private Date createdDate;
    private Long lastModifiedBy;
    private Date lastModifiedDate;
}
