package wiki.thin.storage.github;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import wiki.thin.common.util.FileUtils;
import wiki.thin.entity.GithubStorage;
import wiki.thin.exception.UnexpectedException;
import wiki.thin.storage.StorageFileType;
import wiki.thin.storage.StorageService;

import java.io.IOException;
import java.net.URI;

/**
 * @author Beldon
 */
public class GithubStorageService implements StorageService {


    /**
     * https://raw.githubusercontent.com/{owner}/{repo}/{branch}/{path}
     */
    public static final String GHTHUB_RAW_URL = "https://cdn.jsdelivr.net/gh/{owner}/{repo}@{branch}/{path}";

    private final GitHub github;
    private final GHRepository repository;
    private final GithubStorage githubStorage;

    public GithubStorageService(GithubStorage githubStorage) {
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
        String basePath = githubStorage.getBasePath();
        if (StorageFileType.IMAGE.equals(storageFileType)) {
            basePath = basePath + "/" + "images";
        }
        return basePath + "/" + FileUtils.generateRelativePath(originalFileName);
    }

    @Override
    public URI getUri(String relativePath) {
        final String url = GHTHUB_RAW_URL
                .replaceAll("\\{owner}", githubStorage.getOwner())
                .replaceAll("\\{repo}", githubStorage.getRepo())
                .replaceAll("\\{branch}", githubStorage.getBranch())
                .replaceAll("\\{path}", relativePath);

        return URI.create(url);
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

}
