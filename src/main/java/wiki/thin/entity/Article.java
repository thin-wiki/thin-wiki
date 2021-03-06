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
public class Article extends BaseEntity {

    private String title;
    private String content;

    /**
     * 父id，若为 0 则表示 column 一级
     */
    private Long parentId;
    private Long columnId;
    private SharableEnum sharable;
    private Integer version;
    private Long createdBy;
    private Date createdDate;
    private Long lastModifiedBy;
    private Date lastModifiedDate;
}
