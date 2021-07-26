package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;
import wiki.thin.storage.StorageType;
import wiki.thin.storage.StorageWorkType;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("storage")
public class Storage extends BaseEntity {
    private String name;
    private String description;
    /**
     * 工作类型
     */
    private StorageWorkType workType;
    /**
     * 存储类型
     */
    private StorageType refStorageType;
    /**
     * 存储类型的id
     */
    private Long refStorageId;

    /**
     * 主库id，当库类型是 备库时候，该字段有意义
     */
    private Long mainStorageId;

    /**
     * 是否可写
     */
    private Boolean writable;
}
