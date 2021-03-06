package wiki.thin.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author Beldon
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LocalStorage extends BaseEntity {

    private String name;

    private String description;

    /**
     * base path
     */
    private String basePath;

    private Long createdBy;

    private Date createdDate;

    private Long lastModifiedBy;

    private Date lastModifiedDate;
}
