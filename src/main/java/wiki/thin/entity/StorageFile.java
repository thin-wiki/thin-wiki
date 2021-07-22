package wiki.thin.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Beldon
 */
@Data
@Table("storage_file")
public class StorageFile implements Serializable {
    @Id
    private Long id;
    private Long targetId;
    private String originalFileName;
    private String suffix;
    private Long fileSize;
    private String contentType;
    /**
     * 相对路径
     */
    private String relativePath;
    /**
     * 存储类型id
     */
    private Long storageId;
    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date lastModifiedDate;
}
