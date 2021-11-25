package wiki.thin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Beldon
 */
@Data
public abstract class BaseEntity implements Serializable {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
}
