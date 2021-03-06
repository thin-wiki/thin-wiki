package wiki.thin.service.impl;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wiki.thin.config.properties.CommonProperties;
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

    private final String issuer;

    private final SecretKey secretKey;

    private final Long expiration;

    private final JwtParser jwtParser;

    public JwtServiceImpl(CommonProperties commonProperties) {
        secretKey = new SecretKeySpec(commonProperties.getJwtSecretKey().getBytes(StandardCharsets.UTF_8),
                SignatureAlgorithm.HS512.getJcaName());
        issuer = commonProperties.getJwtIssuer();
        expiration = commonProperties.getJwtExpiration();

        jwtParser = Jwts.parserBuilder().setSigningKey(secretKey)
                .build();
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
                .setIssuer(issuer)
                .setExpiration(Date.from(OffsetDateTime.now().plus(expiration, SECONDS).toInstant()))
                .setIssuedAt(new Date())
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String parseToken(String token) {
        return jwtParser.parseClaimsJws(token).getBody().getSubject();
    }
}
