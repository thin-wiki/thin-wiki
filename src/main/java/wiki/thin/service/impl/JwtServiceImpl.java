package wiki.thin.service.impl;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wiki.thin.constant.CommonConstant;
import wiki.thin.constant.ConfigConstant;
import wiki.thin.service.AppConfigService;
import wiki.thin.service.JwtService;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * https://mozillazg.com/2015/06/hello-jwt.html
 *
 * @author beldon
 */
@Service
public class JwtServiceImpl implements JwtService {

    private final AppConfigService appConfigService;

    public JwtServiceImpl(AppConfigService appConfigService) {
        this.appConfigService = appConfigService;
    }

    @Override
    public boolean isValid(String token) {
        if (!StringUtils.hasText(token)) {
            return false;
        }
        try {
            parseToken(token);
            return true;
        } catch (SignatureException e) {
            return false;
        }
    }

    @Override
    public String generateToken(String account) {
        return Jwts.builder()
                .setSubject(account)
                .setId(UUID.randomUUID().toString())
                .setIssuer(getIssuer())
                .setExpiration(Date.from(OffsetDateTime.now().plus(getExpiration(), SECONDS).toInstant()))
                .setIssuedAt(new Date())
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public String parseToken(String token) {
        return getJwtParser().parseClaimsJws(token).getBody().getSubject();
    }

    private SecretKey getSecretKey() {
        final String jwtSecretKey = appConfigService.getSystemConfigValue(ConfigConstant.SYS_REMEMBER_ME_SECRET_KEY,
                () -> UUID.randomUUID().toString());
        return new SecretKeySpec(jwtSecretKey.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS512.getJcaName());
    }

    private JwtParser getJwtParser() {
        return Jwts.parserBuilder().setSigningKey(getSecretKey()).build();
    }

    private String getIssuer() {
        return CommonConstant.DEFAULT_REMEMBER_ME_JWT_ISSUER;
    }

    private int getExpiration() {
        return CommonConstant.DEFAULT_REMEMBER_ME_EXPIRY;
    }

}
