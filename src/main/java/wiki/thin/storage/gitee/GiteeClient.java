package wiki.thin.storage.gitee;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import wiki.thin.entity.GiteeStorage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Beldon
 */
public class GiteeClient {
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_0_1) "
            + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36";

    private final GiteeStorage giteeStorage;

    private final RestTemplate restTemplate = new RestTemplate();

    public GiteeClient(GiteeStorage giteeStorage) {
        this.giteeStorage = giteeStorage;
    }

    /**
     * 上传文件
     *
     * @param path       文件路径
     * @param base64Data 文件base64 内容
     * @throws IOException exception
     */
    public void uploadFile(String path, String base64Data) throws IOException {
        final String fullUrl = getFullUrl(path);
        try {
            restTemplate.postForEntity(fullUrl, createRequest("", path, base64Data), String.class);
        } catch (RuntimeException e) {
            throw new IOException(e);
        }
    }

    /**
     * 上传文件
     *
     * @param sha        file sha
     * @param path       文件路径
     * @param base64Data 文件base64 内容
     * @throws IOException exception
     */
    public void replaceFile(String sha, String path, String base64Data) throws IOException {
        final String fullUrl = getFullUrl(path);
        try {
            restTemplate.exchange(fullUrl, HttpMethod.PUT, createRequest(sha, path, base64Data), String.class);
        } catch (RuntimeException e) {
            throw new IOException(e);
        }
    }

    private HttpEntity<Map<String, String>> createRequest(String sha, String path, String base64Data) {
        Map<String, String> map = new HashMap<>(16);
        map.put("message", "upload_file");
        map.put("access_token", giteeStorage.getToken());
        map.put("branch", giteeStorage.getBranch());
        map.put("content", base64Data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.ALL));
        headers.put("User-Agent", List.of(USER_AGENT));

        return new HttpEntity<>(map, headers);
    }

    private String getFullUrl(String path) {
        return GitConstant.FILE_API
                .replaceAll("\\{owner}", giteeStorage.getOwner())
                .replaceAll("\\{repo}", giteeStorage.getRepo())
                .replaceAll("\\{path}", path);
    }
}
