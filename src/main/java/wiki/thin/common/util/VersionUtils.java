package wiki.thin.common.util;

/**
 * @author beldon
 */
public class VersionUtils {

    public static boolean isNewVersion(String localVersion, String onlineVersion) {
        if (localVersion.equals(onlineVersion)) {
            return false;
        }
        String[] localArray = localVersion.split("\\.");
        String[] onlineArray = onlineVersion.split("\\.");

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
}
