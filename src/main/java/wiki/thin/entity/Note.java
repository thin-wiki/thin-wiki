package wiki.thin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "note")
public class Note extends BaseEntity {
    @TableField(value = "content")
    private String content;

    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private Long createdBy;

    @TableField(value = "created_date")
    private Date createdDate;

    @TableField(value = "last_modified_by", fill = FieldFill.INSERT_UPDATE)
    private Long lastModifiedBy;

    @TableField(value = "last_modified_date")
    private Date lastModifiedDate;
}