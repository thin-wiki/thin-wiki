package wiki.thin.service;

/**
 * @author Beldon
 */
public interface PasswordService {
    /**
     * 加密密码
     *
     * @param password password
     * @return 加密密码
     */
    String encode(String password);

    /**
     * 检查密码
     *
     * @param raw        原密码
     * @param encodePass 加密密码
     * @return true-匹配，false-非匹配
     */
    boolean checkPassword(String raw, String encodePass);
}
