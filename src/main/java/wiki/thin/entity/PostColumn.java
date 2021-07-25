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
@Table("post_column")
public class PostColumn extends BaseEntity {
    private String path;
    private String title;
    private String content;
    private SharableEnum sharable;
}
