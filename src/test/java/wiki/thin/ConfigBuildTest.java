package wiki.thin;

import org.junit.jupiter.api.Test;
import org.update4j.Configuration;
import org.update4j.FileMetadata;
import wiki.thin.common.util.RsaUtils;

import java.security.KeyPair;
import java.security.PrivateKey;

public class ConfigBuildTest {

    @Test
    public void test() {
//        mvn test -DskipTests=false -Dtest=ConfigBuildTest#test
//        mvn test -Dmaven.test.skip=false -Dtest=ConfigBuildTest#test
        System.out.println("d-----------------------");
    }

    public static void main(String[] args) {
//        PrivateKey key = ... // Load a private key
        final KeyPair keyPair = RsaUtils.generateKeyPair(1024);
        final PrivateKey key = keyPair.getPrivate();

        var cb = Configuration.builder()

                // base URI from where to download, overridable in
                // each individual file setting
                .baseUri("https://example.com/")

                // base path where to save on client machine, overridable in
                // each individual file setting
                .basePath("/home/myapp/")
                .signer(key) // used to sign the files

                // List this property
                .property("app.name", "MyApplication")

                // Automatically resolves system property
                .property("user.location", "${user.home}/myapp/");

        // List this file, uri and path are same as filename
        // Read metadata from real file on dev machine
        // Will be dynamically loaded on the modulepath
//        cb.file(FileMetadata.readFrom("myFile.jar").modulepath())
        cb.file(FileMetadata.readFrom("./data/setup.xml").path("data/myFile.jar"));

        // Path will be same as uri
//                // Will be dynamically loaded on the classpath
//                .file(FileMetadata.readFrom("myFile2.jar")
//                        .uri("file.zip")
//                        .classpath())
//
//                // Will not be loaded on any path, ideal for bootstrap dependencies
//                // that are loaded on JVM startup,
//                // or non jar files
//                .file(FileMetatdata.readFrom("my-resource.png")
//                        .path("${user.location}/my-resource.png")
//                        .uri("https://example.com/pix/my-picture.png"));
//
//        // adds the whole directory and marks all jar files with 'classpath'
//        // getSource() returns the path of the real file.
//             .files(FileMetadata.streamDirectory("target")
//                .peek(r -> r.classpath(r.getSource().toString().endsWith(".jar"))));
        final Configuration build = cb.build();
        System.out.println(build.getResolvedProperty("app.name"));
        System.out.println(build.generateXmlMapper().toXml());


    }
}
