package wiki.thin.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

/**
 * @author Beldon
 */
public interface StorageService {

    /**
     * 保存文件
     *
     * @param file     file
     * @param targetId targetId
     * @return 文件id
     * @throws IOException exception
     */
    StoredFile store(MultipartFile file, Long targetId) throws IOException;

    /**
     * 保存文件
     *
     * @param data         上传的文件
     * @param relativePath relative path
     * @throws IOException exception
     */
    void saveFile(byte[] data, String relativePath) throws IOException;

    /**
     * 文件地址， http:// file:// ftp://
     *
     * @param fileId file id
     * @return uri
     */
    URI getUri(Long fileId);


    /**
     * storage type
     *
     * @return storage id
     */
    Long storageId();
}
