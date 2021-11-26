package wiki.thin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "storage_file")
public class StorageFile extends BaseEntity {
    @TableField(value = "target_id")
    private Long targetId;

    @TableField(value = "original_file_name")
    private String originalFileName;

    @TableField(value = "suffix")
    private String suffix;

    @TableField(value = "file_size")
    private Long fileSize;

    @TableField(value = "content_type")
    private String contentType;

    @TableField(value = "relative_path")
    private String relativePath;

    @TableField(value = "storage_id")
    private Long storageId;

    @TableField(value = "created_by")
    private Long createdBy;

    @TableField(value = "created_date")
    private Date createdDate;

    @TableField(value = "last_modified_by")
    private Long lastModifiedBy;

    @TableField(value = "last_modified_date")
    private Date lastModifiedDate;
}