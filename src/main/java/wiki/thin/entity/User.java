package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {
    private String account;

    private String password;

    private Date lastLoginTime;

    private Date createdDate;

    private Date lastModifiedDate;

    private Long lastModifiedBy;
}
