package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("storage_file")
public class StorageFile extends BaseEntity {
    private Long targetId;
    private String originalFileName;
    private String suffix;
    private Long fileSize;
    private String contentType;
    /**
     * 相对路径
     */
    private String relativePath;
    /**
     * 存储类型id
     */
    private Long storageId;
}
