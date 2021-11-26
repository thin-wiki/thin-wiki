package wiki.thin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "local_storage")
public class LocalStorage extends BaseEntity {
    @TableField(value = "`name`")
    private String name;

    @TableField(value = "description")
    private String description;

    @TableField(value = "base_path")
    private String basePath;

    @TableField(value = "created_by")
    private Long createdBy;

    @TableField(value = "created_date")
    private Date createdDate;

    @TableField(value = "last_modified_by")
    private Long lastModifiedBy;

    @TableField(value = "last_modified_date")
    private Date lastModifiedDate;
}