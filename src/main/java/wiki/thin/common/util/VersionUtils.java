package wiki.thin.common.util;

/**
 * @author beldon
 */
public class VersionUtils {

    private static final String SNAPSHOT_TAG = "-";

    public static boolean isNewVersion(String localVersion, String onlineVersion) {
        if (localVersion.equals(onlineVersion)) {
            return false;
        }
        String[] localArray = split(localVersion);
        String[] onlineArray = split(onlineVersion);

        int length = Math.min(localArray.length, onlineArray.length);

        for (int i = 0; i < length; i++) {
            final var onlineVar = Integer.valueOf(onlineArray[i]);
            final var localVar = Integer.valueOf(localArray[i]);
            if (onlineVar.equals(localVar)) {
                continue;
            }
            return onlineVar > localVar;
        }
        return true;
    }

    private static String[] split(String version) {
        if (version.contains(SNAPSHOT_TAG)) {
            version = version.substring(0, version.indexOf(SNAPSHOT_TAG));
        }
        return version.split("\\.");
    }
}
