package wiki.thin.module.backup;

import lombok.Data;

import java.util.Date;

/**
 * @author beldon
 */
@Data
public class BackupFile {
    private String fileName;
    private Long length;
    private Date lastModified;
}
