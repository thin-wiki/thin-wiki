package wiki.thin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import wiki.thin.constant.enums.SharableEnum;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "article_column")
public class ArticleColumn extends BaseEntity {
    @TableField(value = "`path`")
    private String path;

    @TableField(value = "title")
    private String title;

    @TableField(value = "content")
    private String content;

    /**
     * 0-私有的，1-公开的，2-继承
     */
    @TableField(value = "sharable")
    private SharableEnum sharable;

    @TableField(value = "created_by")
    private Long createdBy;

    @TableField(value = "created_date")
    private Date createdDate;

    @TableField(value = "last_modified_by")
    private Long lastModifiedBy;

    @TableField(value = "last_modified_date")
    private Date lastModifiedDate;
}