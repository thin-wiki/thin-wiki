package wiki.thin.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Beldon
 */
@Data
@Table("gitee_storage")
public class GiteeStorage implements Serializable {
    @Id
    private Long id;
    private String name;
    private String description;
    private String token;
    private String owner;
    private String repo;
    private String branch;
    private String basePath;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
