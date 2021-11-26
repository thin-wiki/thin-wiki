package wiki.thin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import wiki.thin.constant.enums.SharableEnum;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "article")
public class Article extends BaseEntity {
    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    @TableField(value = "parent_id")
    private Long parentId;

    @TableField(value = "column_id")
    private Long columnId;

    /**
     * 0-私有的，1-公开的，2-继承
     */
    @TableField(value = "sharable")
    private SharableEnum sharable;

    /**
     * 状态，0-正常，-1 回收站中
     */
    @TableField(value = "`status`")
    private Boolean status;

    @TableField(value = "version")
    private Integer version;

    @TableField(value = "created_by")
    private Long createdBy;

    @TableField(value = "created_date")
    private Date createdDate;

    @TableField(value = "last_modified_by")
    private Long lastModifiedBy;

    @TableField(value = "last_modified_date")
    private Date lastModifiedDate;
}