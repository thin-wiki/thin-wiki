package wiki.thin.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;
import wiki.thin.constant.enums.SharableEnum;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Beldon
 */
@Data
@Table("post")
public class Post implements Serializable {
    @Id
    private Long id;
    private String title;
    private String content;

    /**
     * 父id，若为 0 则表示 column 一级
     */
    private Long parentId;
    private Long columnId;
    private SharableEnum sharable;
    private Integer status;
    @Version
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
