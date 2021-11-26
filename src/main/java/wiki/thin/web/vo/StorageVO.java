package wiki.thin.web.vo;

import lombok.Data;
import wiki.thin.module.storage.StorageType;
import wiki.thin.module.storage.StorageWorkType;

import java.util.Date;

/**
 * @author Beldon
 */
@Data
public class StorageVO {
    private Long id;

    private String name;

    private String description;

    private StorageWorkType workType;

    private StorageType refStorageType;

    private Long refStorageId;

    private String refStorageName;

    private Long mainStorageId;

    private String mainStorageName;

    private Boolean writable;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
