package wiki.thin.storage.github;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import wiki.thin.entity.GithubStorage;
import wiki.thin.entity.Storage;
import wiki.thin.entity.StorageFile;
import wiki.thin.exception.UnexpectedException;
import wiki.thin.mapper.StorageFileMapper;
import wiki.thin.storage.BaseStorageService;
import wiki.thin.storage.StorageFileType;

import java.io.IOException;
import java.net.URI;

/**
 * @author Beldon
 */
public class GithubStorageService extends BaseStorageService {


    /**
     * https://raw.githubusercontent.com/{owner}/{repo}/{branch}/{path}
     */
    public static final String GHTHUB_RAW_URL = "https://cdn.jsdelivr.net/gh/{owner}/{repo}@{branch}/{path}";

    private final GitHub github;
    private final GHRepository repository;
    private final GithubStorage githubStorage;

    public GithubStorageService(StorageFileMapper storageFileMapper, Storage storage, GithubStorage githubStorage) {
        super(storage, storageFileMapper);
        this.githubStorage = githubStorage;
        try {
            github = new GitHubBuilder().withOAuthToken(githubStorage.getToken()).build();
            String repo = githubStorage.getOwner() + "/" + githubStorage.getRepo();
            repository = github.getRepository(repo);
        } catch (IOException e) {
            throw new UnexpectedException("", e);
        }
    }

    @Override
    public String getRelativePath(StorageFileType storageFileType, String originalFileName) {
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
    protected URI getUri(StorageFile file) {
        final String relativePath = file.getRelativePath();

        final String url = GHTHUB_RAW_URL
                .replaceAll("\\{owner}", githubStorage.getOwner())
                .replaceAll("\\{repo}", githubStorage.getRepo())
                .replaceAll("\\{branch}", githubStorage.getBranch())
                .replaceAll("\\{path}", relativePath);

        return URI.create(url);
    }
}
