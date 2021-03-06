package wiki.thin.storage.gitee;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StreamUtils;

import java.io.FileInputStream;
import java.util.Base64;

@SpringBootTest
class GiteeClientTest {

    @Autowired
    private GiteeClient giteeClient;

    @Test
    void uploadFile() throws Exception {
        final byte[] bytes = StreamUtils.copyToByteArray(new FileInputStream("./mvnw"));
        final Base64.Encoder encoder = Base64.getEncoder();
        final String base64 = encoder.encodeToString(bytes);
        giteeClient.uploadFile("22.txt", base64);
    }
}