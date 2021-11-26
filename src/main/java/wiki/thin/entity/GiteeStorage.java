package wiki.thin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "gitee_storage")
public class GiteeStorage extends BaseEntity {
    @TableField(value = "`name`")
    private String name;

    @TableField(value = "description")
    private String description;

    @TableField(value = "token")
    private String token;

    @TableField(value = "`owner`")
    private String owner;

    @TableField(value = "repo")
    private String repo;

    @TableField(value = "branch")
    private String branch;

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