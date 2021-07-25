package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("local_storage")
public class LocalStorage extends BaseEntity {
    private String name;
    private String description;
    /**
     * base path
     */
    private String basePath;
}
