package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("gitee_storage")
public class GiteeStorage extends BaseEntity {
    private String name;
    private String description;
    private String token;
    private String owner;
    private String repo;
    private String branch;
    private String basePath;
}
