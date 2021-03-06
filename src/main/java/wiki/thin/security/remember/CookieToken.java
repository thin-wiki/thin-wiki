package wiki.thin.security.remember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Beldon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class CookieToken {
    private long expiryTime;

    private String account;

    private String signature;
}
