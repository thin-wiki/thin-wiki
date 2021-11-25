package wiki.thin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "app_config")
public class AppConfig extends BaseEntity {
    @TableField(value = "`type`")
    private String type;

    @TableField(value = "`key`")
    private String key;

    @TableField(value = "`value`")
    private String value;

    @TableField(value = "description")
    private String description;

    @TableField(value = "created_by")
    private Long createdBy;

    @TableField(value = "created_date")
    private Date createdDate;

    @TableField(value = "last_modified_by")
    private Long lastModifiedBy;

    @TableField(value = "last_modified_date")
    private Date lastModifiedDate;
}