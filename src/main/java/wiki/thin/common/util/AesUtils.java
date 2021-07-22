//package wiki.thin.common.util;
//
//import wiki.thin.exception.DecryptException;
//import wiki.thin.exception.EncryptException;
//
//import javax.crypto.*;
//import javax.crypto.spec.SecretKeySpec;
//import java.security.InvalidKeyException;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//import java.util.Base64;
//
///**
// * @author beldon
// */
//public class AesUtils {
//    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
//
//    public static String encrypt(String seed, String rawText) {
//        try {
//            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//            cipher.init(Cipher.ENCRYPT_MODE, getKey(seed));
//            byte[] result = cipher.doFinal(rawText.getBytes());
//            return Base64.getEncoder().encodeToString(result);
//        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException
//                | BadPaddingException | IllegalBlockSizeException e) {
//            throw new EncryptException(e);
//        }
//    }
//
//    public static String decrypt(String seed, String encryptedText) {
//        try {
//            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//            cipher.init(Cipher.DECRYPT_MODE, getKey(seed));
//            byte[] result = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
//            return new String(result);
//        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException
//                | NoSuchAlgorithmException | InvalidKeyException e) {
//            throw new DecryptException(e);
//        }
//    }
//
//    public static Key getKey(String seed) throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
//        secureRandom.setSeed(seed.getBytes());
//        keyGenerator.init(256, secureRandom);
//        SecretKey secretKey = keyGenerator.generateKey();
//        return new SecretKeySpec(secretKey.getEncoded(), "AES");
//    }
//}
