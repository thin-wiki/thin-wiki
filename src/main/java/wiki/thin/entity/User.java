package wiki.thin.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author beldon
 */
@Data
@Table("user")
public class User implements Serializable {
    @Id
    private Long id;
    private String account;
    private String password;
    private LocalDateTime lastLoginTime;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
