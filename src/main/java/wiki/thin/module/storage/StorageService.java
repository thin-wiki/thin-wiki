package wiki.thin.module.storage;

import java.io.IOException;
import java.net.URI;

/**
 * @author Beldon
 */
public interface StorageService {

    /**
     * 保存文件
     * store
     *
     * @param data         上传的文件
     * @param relativePath relative path
     * @throws IOException exception
     */
    void saveFile(byte[] data, String relativePath) throws IOException;

    /**
     * 获取文件类型
     *
     * @param storageFileType  file type
     * @param originalFileName originalFileName
     * @return relative path
     */
    String getRelativePath(StorageFileType storageFileType, String originalFileName);

    /**
     * 文件地址， http:// file:// ftp://
     *
     * @param relativePath 相对路径
     * @return uri
     */
    URI getUri(String relativePath);

}
