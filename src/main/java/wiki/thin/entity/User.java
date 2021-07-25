package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * @author beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("user")
public class User extends BaseEntity {

    private String account;
    private String password;
    private LocalDateTime lastLoginTime;
}
