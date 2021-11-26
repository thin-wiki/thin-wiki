package wiki.thin.module.storage.gitee;

import wiki.thin.common.util.FileUtils;
import wiki.thin.entity.GiteeStorage;
import wiki.thin.module.storage.StorageFileType;
import wiki.thin.module.storage.StorageService;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;

/**
 * @author Beldon
 */
public class GiteeStorageService implements StorageService {

    private final GiteeClient giteeClient;

    private final GiteeStorage giteeStorage;

    public GiteeStorageService(GiteeStorage giteeStorage) {
        this.giteeClient = new GiteeClient(giteeStorage);
        this.giteeStorage = giteeStorage;
    }

    @Override
    public String getRelativePath(StorageFileType storageFileType, String originalFileName) {
        String basePath = giteeStorage.getBasePath();
        if (StorageFileType.IMAGE.equals(storageFileType)) {
            basePath = basePath + "/" + "images";
        }
        return basePath + "/" + FileUtils.generateRelativePath(originalFileName);
    }

    @Override
    public URI getUri(String relativePath) {

        final String url = GitConstant.HTML_URL
                .replaceAll("\\{owner}", giteeStorage.getOwner())
                .replaceAll("\\{repo}", giteeStorage.getRepo())
                .replaceAll("\\{branch}", giteeStorage.getBranch())
                .replaceAll("\\{path}", relativePath);

        return URI.create(url);
    }

    @Override
    public void saveFile(byte[] data, String relativePath) throws IOException {
        final String dataStr = Base64.getEncoder().encodeToString(data);
        giteeClient.uploadFile(relativePath, dataStr);
    }

}
