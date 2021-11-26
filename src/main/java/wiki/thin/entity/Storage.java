package wiki.thin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wiki.thin.module.storage.StorageType;
import wiki.thin.module.storage.StorageWorkType;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "`storage`")
public class Storage extends BaseEntity {
    @TableField(value = "`name`")
    private String name;

    @TableField(value = "description")
    private String description;

    @TableField(value = "work_type")
    private StorageWorkType workType;

    @TableField(value = "ref_storage_type")
    private StorageType refStorageType;

    @TableField(value = "ref_storage_id")
    private Long refStorageId;

    @TableField(value = "main_storage_id")
    private Long mainStorageId;

    @TableField(value = "writable")
    private Boolean writable;

    @TableField(value = "created_by")
    private Long createdBy;

    @TableField(value = "created_date")
    private Date createdDate;

    @TableField(value = "last_modified_by")
    private Long lastModifiedBy;

    @TableField(value = "last_modified_date")
    private Date lastModifiedDate;
}