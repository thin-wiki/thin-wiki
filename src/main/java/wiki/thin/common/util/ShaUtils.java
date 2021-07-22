//package wiki.thin.common.util;
//
//import lombok.extern.slf4j.Slf4j;
//
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
///**
// * @author Beldon.
// */
//@Slf4j
//public class ShaUtils {
//
//    private ShaUtils() {
//
//    }
//
//    public static String sha256(String key) {
//        String encodeStr = "";
//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//            messageDigest.update(key.getBytes(StandardCharsets.UTF_8));
//            encodeStr = byte2Hex(messageDigest.digest());
//        } catch (NoSuchAlgorithmException e) {
//            log.error("not sha256 algorithm", e);
//        }
//        return encodeStr;
//    }
//
//    private static String byte2Hex(byte[] bytes) {
//        StringBuilder stringBuffer = new StringBuilder();
//        String temp;
//        for (byte aByte : bytes) {
//            temp = Integer.toHexString(aByte & 0xFF);
//            if (temp.length() == 1) {
//                stringBuffer.append("0");
//            }
//            stringBuffer.append(temp);
//        }
//        return stringBuffer.toString();
//    }
//
//}
