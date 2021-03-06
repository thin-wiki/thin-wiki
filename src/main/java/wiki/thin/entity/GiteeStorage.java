package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GiteeStorage extends BaseEntity {
    private String name;

    private String description;

    private String token;

    private String owner;

    private String repo;

    private String branch;

    private String basePath;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
