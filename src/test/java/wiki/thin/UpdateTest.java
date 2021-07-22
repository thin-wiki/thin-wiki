//package wiki.thin;
//
//import org.update4j.Archive;
//import org.update4j.Configuration;
//import org.update4j.UpdateOptions;
//import org.update4j.UpdateResult;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.file.Files;
//import java.nio.file.Path;
//
//public class UpdateTest {
//    public static void main(String[] args) throws IOException {
////        URL url = new URL("http://docs.update4j.org/demo/setup.xml");
//        final InputStream is = Files.newInputStream(Path.of("data", "setup.xml"));
//        InputStreamReader in = new InputStreamReader(is);
//        Configuration config = Configuration.read(in);
////        UpdateOptions.archive().updateHandler(new UpdateHandler() {
////            @Override
////            public void startCheckUpdates() throws Throwable {
////
////            }
////        })
//        UpdateResult result = config.update(UpdateOptions.archive(Path.of("./data/update.zip")));
//        Archive.read("./data/update.zip").install();
////        config.update(UpdateOptions.archive(Path.of("./data/update.zip")));
////        config.launch();
////        UpdateHandler
//    }
//
//}
