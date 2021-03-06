package wiki.thin.common.util;

import java.net.URI;

/**
 * @author Beldon
 */
public class UriUtil {

    private static final String FILE_SUFFIX = "file";

    private static final String FILE_HTTP = "http";

    public static boolean isFile(URI uri) {
        return uri != null && uri.toString().startsWith(FILE_SUFFIX);
    }

    public static boolean isHttp(URI uri) {
        return uri != null && uri.toString().startsWith(FILE_HTTP);
    }
}
