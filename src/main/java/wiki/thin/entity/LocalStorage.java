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
@Table("local_storage")
public class LocalStorage implements Serializable {
    @Id
    private Long id;
    private String name;
    private String description;
    /**
     * base path
     */
    private String basePath;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
