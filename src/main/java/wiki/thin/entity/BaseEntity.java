package wiki.thin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Beldon
 */
@Data
public abstract class BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
//    @TableField("created_by")
//    private Long createdBy;
//    @TableField("created_date")
//    private Date createdDate;
//    @TableField("last_modified_by")
//    private Long lastModifiedBy;
//    @TableField("last_modified_date")
//    private Date lastModifiedDate;
}
