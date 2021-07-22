package wiki.thin.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import wiki.thin.constant.enums.SharableEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Beldon
 */
@Data
@Table("post_history")
public class PostHistory implements Serializable {
    @Id
    private Long id;
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
    @CreatedDate
    private LocalDateTime createdDate;
}
