package wiki.thin.module.storage;

import lombok.Data;

/**
 * @author Beldon
 */
@Data
public class StoredFile {
    private final Long fileId;

    private final String relativePath;
}
