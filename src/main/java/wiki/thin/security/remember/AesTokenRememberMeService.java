package wiki.thin.security.remember;

import wiki.thin.common.util.AesUtils;
import wiki.thin.constant.ConfigConstant;
import wiki.thin.entity.User;
import wiki.thin.mapper.UserMapper;
import wiki.thin.service.AppConfigService;

import java.util.UUID;

/**
 * @author Beldon
 */
public class AesTokenRememberMeService extends BaseRememberMeService {
    private static final String SEPARATOR = "#";
    private final AppConfigService appConfigService;

    public AesTokenRememberMeService(UserMapper userMapper, AppConfigService appConfigService) {
        super(userMapper);
        this.appConfigService = appConfigService;
    }

    @Override
    protected CookieToken makeToken(User user) {
        String token = System.currentTimeMillis() + SEPARATOR + user.getAccount();
        String signature = AesUtils.encrypt(getSecret(), token);
        return new CookieToken(getExpiry(), user.getAccount(), signature);
    }

    @Override
    protected boolean isValidSignature(User user, CookieToken cookieToken) {
        try {
            final String rawToken = AesUtils.decrypt(getSecret(), cookieToken.getSignature());
            final String[] arr = rawToken.split(SEPARATOR);
            long loginTime = Long.parseLong(arr[0]);
            String account = arr[1];
            boolean isTokenExpired = System.currentTimeMillis() < (loginTime + getExpiry() * 1000L);
            return user.getAccount().equals(account) && isTokenExpired;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected boolean isTokenExpired(CookieToken cookieToken) {
        try {
            final String rawToken = AesUtils.decrypt(getSecret(), cookieToken.getSignature());
            final String[] arr = rawToken.split(SEPARATOR);
            long loginTime = Long.parseLong(arr[0]);
            return System.currentTimeMillis() > (loginTime + getExpiry() * 1000L);
        } catch (Exception e) {
            return false;
        }
    }

    private String getSecret() {
        return appConfigService.getSystemConfigValue(ConfigConstant.SYS_REMEMBER_ME_SECRET_KEY,
                () -> UUID.randomUUID().toString());
    }
}
