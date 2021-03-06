package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
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

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
