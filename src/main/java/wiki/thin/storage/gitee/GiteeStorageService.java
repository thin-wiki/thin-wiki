package wiki.thin.storage.gitee;

import wiki.thin.entity.GiteeStorage;
import wiki.thin.entity.Storage;
import wiki.thin.entity.StorageFile;
import wiki.thin.mapper.StorageFileMapper;
import wiki.thin.storage.BaseStorageService;
import wiki.thin.storage.StorageFileType;

import java.io.IOException;
import java.net.URI;
import java.util.Base64;

/**
 * @author Beldon
 */
public class GiteeStorageService extends BaseStorageService {

    private final GiteeClient giteeClient;

    private final GiteeStorage giteeStorage;

    public GiteeStorageService(StorageFileMapper storageFileMapper,
                               Storage storage,
                               GiteeStorage giteeStorage) {
        super(storage, storageFileMapper);
        this.giteeClient = new GiteeClient(giteeStorage);
        this.giteeStorage = giteeStorage;
    }

    @Override
    protected String getRelativePath(StorageFileType storageFileType, String originalFileName) {
        String bastPath = giteeStorage.getBasePath();
        if (StorageFileType.IMAGE.equals(storageFileType)) {
            bastPath = bastPath + "/" + "images";
        }
        return bastPath + "/" + super.getRelativePath(storageFileType, originalFileName);
    }

    @Override
    public void saveFile(byte[] data, String relativePath) throws IOException {
        final String dataStr = Base64.getEncoder().encodeToString(data);
        giteeClient.uploadFile(relativePath, dataStr);
    }

    @Override
    protected URI getUri(StorageFile file) {
        final String relativePath = file.getRelativePath();

        final String url = GitConstant.HTML_URL
                .replaceAll("\\{owner}", giteeStorage.getOwner())
                .replaceAll("\\{repo}", giteeStorage.getRepo())
                .replaceAll("\\{branch}", giteeStorage.getBranch())
                .replaceAll("\\{path}", relativePath);

        return URI.create(url);
    }

}
