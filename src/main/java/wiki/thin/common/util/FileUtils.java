package wiki.thin.common.util;

import org.springframework.util.StringUtils;

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
}
