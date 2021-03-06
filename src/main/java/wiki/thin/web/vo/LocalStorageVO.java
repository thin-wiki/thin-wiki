package wiki.thin.web.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Beldon
 */
@Data
public class LocalStorageVO {
    private Long id;

    private String name;

    private String description;

    private String basePath;

    private String createdBy;

    private Date createdDate;

    private String lastModifiedBy;

    private Date lastModifiedDate;
}
