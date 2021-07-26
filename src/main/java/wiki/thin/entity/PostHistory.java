package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;
import wiki.thin.constant.enums.SharableEnum;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("post_history")
public class PostHistory extends BaseEntity {
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
}
