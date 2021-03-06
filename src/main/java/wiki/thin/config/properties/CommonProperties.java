package wiki.thin.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * common properties.
 *
 * @author beldon
 */
@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class CommonProperties {
    /**
     * jwt secret key.
     */
    private String jwtSecretKey;

    /**
     * jwt issuer.
     */
    private String jwtIssuer = "thin-wiki";

    /**
     * default 30 days.
     */
    private Long jwtExpiration = 60 * 60 * 24 * 30L;

    /**
     * jwt header key, default Authorization.
     */
    private String jwtHeaderKey = "Authorization";

}
