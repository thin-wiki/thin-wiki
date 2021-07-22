package wiki.thin.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Beldon
 */
@Data
@Table("post_view_history")
public class PostViewHistory implements Serializable {
    @Id
    private Long id;
    private Long articleId;
    private Long viewCount;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;
}
