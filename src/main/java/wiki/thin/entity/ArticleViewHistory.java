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
@TableName(value = "article_view_history")
public class ArticleViewHistory extends BaseEntity {
    @TableField(value = "article_id")
    private Long articleId;

    @TableField(value = "created_by")
    private Long createdBy;

    @TableField(value = "last_modified_date")
    private Date lastModifiedDate;
}