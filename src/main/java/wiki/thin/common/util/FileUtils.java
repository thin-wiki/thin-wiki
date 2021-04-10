package wiki.thin.common.util;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import wiki.thin.exception.UnexpectedException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Beldon
 */
public class FileUtils {

    private static final String POINT = ".";

    /**
     * 获取文件后缀
     *
     * @param originalFileName originalFileName
     * @return file name suffix
     */
    public static String getSuffix(String originalFileName) {
        if (!StringUtils.hasText(originalFileName) || !originalFileName.contains(POINT)) {
            return null;
        }

        return originalFileName.substring(originalFileName.lastIndexOf(POINT) + 1);
    }

    public static String generateRelativePath(String originalFileName) {
        return currentDir() + "/" + System.currentTimeMillis() + POINT + getSuffix(originalFileName);
    }

    public static byte[] readAllByte(URI uri) throws IOException {
        byte[] data;
        if (UriUtil.isFile(uri)) {
            final Path path = Paths.get(uri.getPath());
            data = Files.readAllBytes(path);
        } else if (UriUtil.isHttp(uri)) {
            data = readAllByte(uri.toString());
        } else {
            throw new UnexpectedException("not support" + uri.getScheme());
        }
        return data;
    }

    public static byte[] readAllByte(String httpUrl) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 11_2_3) "
                + "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36 Edg/89.0.774.75");
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,"
                + "image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");

        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,"
                + "image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,"
                + "en-US;q=0.6,cy;q=0.5,fr;q=0.4,zh-TW;q=0.3,de;q=0.2,nl;q=0.1");

        ResponseEntity<byte[]> entity = restTemplate.exchange(httpUrl, HttpMethod.GET, new HttpEntity<>(headers), byte[].class);
        return entity.getBody();
    }

    private static String currentDir() {
        return DateUtils.currentYear()
                + File.separator
                + DateUtils.currentMonth()
                + File.separator + DateUtils.currentDay();
    }
}
