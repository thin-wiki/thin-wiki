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
@TableName(value = "article_history")
public class ArticleHistory extends BaseEntity {
    @TableField(value = "article_id")
    private Long articleId;

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

    @TableField(value = "version")
    private Integer version;

    @TableField(value = "modified_by")
    private Long modifiedBy;

    @TableField(value = "modified_date")
    private Date modifiedDate;
}