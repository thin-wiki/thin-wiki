package wiki.thin.common.util;

import org.springframework.util.StringUtils;

import java.io.File;

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

    private static String currentDir() {
        return DateUtils.currentYear()
                + File.separator
                + DateUtils.currentMonth()
                + File.separator + DateUtils.currentDay();
    }
}
