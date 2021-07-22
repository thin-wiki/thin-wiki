package wiki.thin.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import wiki.thin.constant.enums.SharableEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Beldon
 */
@Data
@Table("post_column")
public class PostColumn implements Serializable {
    @Id
    private Long id;
    private String path;
    private String title;
    private String content;
    private SharableEnum sharable;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
