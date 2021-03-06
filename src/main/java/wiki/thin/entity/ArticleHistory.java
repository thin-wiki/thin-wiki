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
public class ArticleHistory extends BaseEntity {

    private Long articleId;
    private String title;
    private String content;
    private Integer version;

    /**
     * 父id，若为 0 则表示 column 一级
     */
    private Long parentId;
    private Long columnId;
    private SharableEnum sharable;
    private Long modifiedBy;
    private Date modifiedDate;
}
