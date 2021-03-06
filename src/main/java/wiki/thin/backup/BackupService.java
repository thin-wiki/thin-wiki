package wiki.thin.backup;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @author Beldon
 */
public interface BackupService {

    /**
     * 备份数据
     *
     * @throws IOException exception
     */
    void backup() throws IOException;

    /**
     * 列出备份文件列表
     *
     * @return 备份文件列表
     */
    List<BackupFile> list();

    /**
     * 还原数据
     *
     * @param fileName file name
     */
    void restore(String fileName);

    /**
     * 删除数据
     *
     * @param fileName file name
     * @throws IOException exception
     */
    void delete(String fileName) throws IOException;

    /**
     * download
     *
     * @param fileName     fileName
     * @param outputStream outputStream
     * @throws IOException exception
     */
    void download(String fileName, OutputStream outputStream) throws IOException;
}
