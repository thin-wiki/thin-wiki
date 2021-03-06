package wiki.thin.storage.github;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import wiki.thin.entity.GithubStorage;
import wiki.thin.entity.Storage;
import wiki.thin.mapper.StorageFileMapper;
import wiki.thin.storage.BaseStorageService;
import wiki.thin.storage.StorageFileType;

import java.io.IOException;
import java.net.URI;

/**
 * @author Beldon
 */
public class GithubStorageService extends BaseStorageService {
    private final GitHub github;

    private final GHRepository repository;

    private final GithubStorage githubStorage;

    protected GithubStorageService(StorageFileMapper storageFileMapper, Storage storage, GithubStorage githubStorage) throws IOException {
        super(storage, storageFileMapper);
        this.githubStorage = githubStorage;
        github = new GitHubBuilder().withOAuthToken(githubStorage.getToken()).build();
        repository = github.getRepository(githubStorage.getRepo());
    }

    @Override
    protected String getRelativePath(StorageFileType storageFileType, String originalFileName) {
        String bastPath = githubStorage.getBasePath();
        if (StorageFileType.IMAGE.equals(storageFileType)) {
            bastPath = bastPath + "/" + "images";
        }
        return bastPath + "/" + super.getRelativePath(storageFileType, originalFileName);
    }

    @Override
    public void saveFile(byte[] data, String relativePath) throws IOException {
        repository.createContent()
                .content(data)
                .branch(githubStorage.getBranch())
                .message("update")
                .path(relativePath)
                .commit();
    }

    @Override
    public URI getUri(Long fileId) {
        throw new RuntimeException("no support");
    }
}
