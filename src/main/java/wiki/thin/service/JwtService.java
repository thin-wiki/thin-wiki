package wiki.thin.service;

import io.jsonwebtoken.security.SignatureException;

/**
 * @author beldon
 */
public interface JwtService {

    /**
     * 校验token是否正确
     *
     * @param token token
     * @return true-有效，false-无效
     */
    boolean isValid(String token);

    /**
     * 生成token
     *
     * @param account 用户id
     * @return token
     */
    String generateToken(String account);

    /**
     * 解析token
     *
     * @param token token
     * @return account
     * @throws SignatureException 无效token时候抛出的异常
     */
    String parseToken(String token);
}
