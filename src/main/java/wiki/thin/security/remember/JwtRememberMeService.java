package wiki.thin.security.remember;

import wiki.thin.entity.User;
import wiki.thin.mapper.UserMapper;
import wiki.thin.service.JwtService;

/**
 * @author Beldon
 */
public class JwtRememberMeService extends BaseRememberMeService {
    private final JwtService jwtService;

    public JwtRememberMeService(UserMapper userMapper, JwtService jwtService) {
        super(userMapper);
        this.jwtService = jwtService;
    }

    @Override
    protected CookieToken makeToken(User user) {
        String jwtToken = jwtService.generateToken(user.getAccount());
        return new CookieToken(getExpiry(), user.getAccount(), jwtToken);
    }

    @Override
    protected boolean isValidSignature(User user, CookieToken cookieToken) {
        return jwtService.isValid(cookieToken.getSignature());
    }

    @Override
    protected boolean isTokenExpired(CookieToken cookieToken) {
        return !jwtService.isValid(cookieToken.getSignature());
    }
}
