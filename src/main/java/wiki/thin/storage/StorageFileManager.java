package wiki.thin.storage;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;

/**
 * @author Beldon
 */
public interface StorageFileManager {

    /**
     * 保存文件
     *
     * @param file     文件
     * @param targetId 目标id
     * @return 文件 id
     * @throws IOException IOException
     */

    Long store(MultipartFile file, Long targetId) throws IOException;

    /**
     * 获取文件 uri
     *
     * @param fileId 文件id
     * @return 文件 uri
     */
    URI getUri(Long fileId);

    /**
     * 清理缓存
     */
    void cleanCache();

    /**
     * 仓库复制
     *
     * @param targetType 目标仓库类型
     * @param targetId   目标仓库id
     */
    @Async
    void copy(StorageType targetType, Long targetId);


}
