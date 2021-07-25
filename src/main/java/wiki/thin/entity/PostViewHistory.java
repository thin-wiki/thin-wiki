package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("post_view_history")
public class PostViewHistory extends BaseEntity {
    private Long postId;
    private Long viewCount;
}
