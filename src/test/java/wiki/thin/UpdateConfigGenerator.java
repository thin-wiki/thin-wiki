//package wiki.thin;
//
//import org.junit.jupiter.api.Test;
//import org.update4j.Configuration;
//import org.update4j.FileMetadata;
//import wiki.thin.common.util.RsaUtils;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.security.KeyPair;
//import java.security.PrivateKey;
//import java.util.Properties;
//import java.util.stream.Stream;
//
///**
// * @author beldon
// */
//public class UpdateConfigGenerator {
//
//    @Test
//    public void generate() throws IOException {
//        final KeyPair keyPair = RsaUtils.generateKeyPair(1024);
//        final PrivateKey key = keyPair.getPrivate();
//
//        final Properties buildProperties = getBuildProperties();
//
//        var cb = Configuration.builder()
//                .baseUri("https://update.thin.wiki/")
//                .basePath("${user.dir}");
////                .signer(key);
//
//        cb.property("app.name", "Thin Wiki");
//        cb.property("build.version", buildProperties.getProperty("build.version", ""));
//        cb.property("build.time", buildProperties.getProperty("build.time", ""));
//
////        System.out.println(Path.of("target", "thin-wiki.jar").toFile().getAbsoluteFile());
////        cb.file(FileMetadata.readFrom(Path.of("target", "thin-wiki.jar"))
//        cb.file(FileMetadata.readFrom(new File("/Users/beldon/Documents/work/me/thin-wiki/target/thin-wiki.jar").toPath())
//                .uri("version/${build.version}/thin-wiki.jar")
//                .path("lib/thin-wiki.jar"));
//
////        final Path path = Paths.get("target", "thin", "root");
////        try (Stream<Path> walk = Files.walk(path)) {
////            walk.forEach(p -> {
////                if (p.toFile().isFile()) {
////                    cb.file(FileMetadata.readFrom(p).path(p.toString().replace("target/thin/root/","")));
////                }
////            });
////        }
//
//        // Path will be same as uri
////                // Will be dynamically loaded on the classpath
////                .file(FileMetadata.readFrom("myFile2.jar")
////                        .uri("file.zip")
////                        .classpath())
////
////                // Will not be loaded on any path, ideal for bootstrap dependencies
////                // that are loaded on JVM startup,
////                // or non jar files
////                .file(FileMetatdata.readFrom("my-resource.png")
////                        .path("${user.location}/my-resource.png")
////                        .uri("https://example.com/pix/my-picture.png"));
////
////        // adds the whole directory and marks all jar files with 'classpath'
////        // getSource() returns the path of the real file.
////             .files(FileMetadata.streamDirectory("target")
////                .peek(r -> r.classpath(r.getSource().toString().endsWith(".jar"))));
//        final Configuration build = cb.build();
//        System.out.println(build.getResolvedProperty("app.name"));
//        System.out.println(build.generateXmlMapper().toXml());
//    }
//
//    private Properties getBuildProperties() throws IOException {
//        Properties properties = new Properties();
//        final InputStream is = ThinApplication.class.getResourceAsStream("/META-INF/build-info.properties");
//        if (is != null) {
//            properties.load(is);
//        }
//        return properties;
//    }
//}
