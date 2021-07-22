//package wiki.thin.security.remember;
//
//import lombok.Getter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.util.StringUtils;
//import wiki.thin.constant.CommonConstant;
//import wiki.thin.entity.User;
//import wiki.thin.exception.CookieParseException;
//import wiki.thin.mapper.UserMapper;
//import wiki.thin.security.AuthType;
//import wiki.thin.security.Authentication;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.nio.charset.StandardCharsets;
//import java.util.Base64;
//import java.util.Date;
//import java.util.Optional;
//
///**
// * @author Beldon
// */
//@Getter
//@Slf4j
//public abstract class BaseRememberMeService implements RememberMeService {
//
//    private static final String DELIMITER = ":";
//
//    private static final int COOKIE_LENGTH = 3;
//
//    private String rememberKey = CommonConstant.DEFAULT_REMEMBER_ME_KEY;
//
//    /**
//     * expire time ,second
//     */
//    private int expiry = CommonConstant.DEFAULT_REMEMBER_ME_EXPIRY;
//
//    private final UserMapper userMapper;
//
//    public BaseRememberMeService(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
//
//
//    /**
//     * 自动登录
//     *
//     * @param request  request
//     * @param response response
//     * @return Authentication
//     */
//    @Override
//    public Optional<Authentication> autoLogin(HttpServletRequest request, HttpServletResponse response) {
//
//        //不存在 cookie
//        Cookie rememberCookie = getRememberCookie(request);
//        if (rememberCookie == null) {
//            return Optional.empty();
//        }
//
//        CookieToken cookieToken = decodeCookie(rememberCookie.getValue());
//        String account = cookieToken.getAccount();
//
//        //找不到用户
//        Optional<User> userOptional = userMapper.findByAccount(account);
//        if (userOptional.isEmpty()) {
//            return Optional.empty();
//        }
//
//        User user = userOptional.get();
//
//        //验签
//        if (!isValidSignature(user, cookieToken)) {
//            return Optional.empty();
//        }
//
//        //是否过期
//        if (isTokenExpired(cookieToken)) {
//            return Optional.empty();
//        }
//
//        Authentication authentication = new Authentication(AuthType.REMEMBER_ME, user);
//
//        //设置更新状态
//        setLoginStatus(authentication, request);
//
//        //生成新的签名，重新更新 cookie
//        CookieToken newToken = setRememberToken(user, response);
//
//        //自动登录成功
//        afterAutoLoginSuccess(request, response, user, cookieToken, newToken);
//
//        return Optional.of(authentication);
//    }
//
//    /**
//     * 普通登录
//     *
//     * @param request  request
//     * @param response response
//     * @param user     user
//     * @return Authentication
//     */
//    @Override
//    public Optional<Authentication> login(HttpServletRequest request, HttpServletResponse response, User user) {
//
//        Authentication authentication = new Authentication(AuthType.AUTHENTICATED, user);
//
//        request.getSession().setAttribute(CommonConstant.Session.AUTHENTICATION, authentication);
//
//        CookieToken cookieToken = setRememberToken(user, response);
//
//        user.setLastLoginTime(new Date());
//
//        userMapper.updateLastLoginTime(user.getId(), new Date());
//
//        afterLoginSuccess(request, response, user, cookieToken);
//
//        return Optional.of(authentication);
//    }
//
//    /**
//     * @param request  request
//     * @param response response
//     */
//    @Override
//    public void logout(HttpServletRequest request, HttpServletResponse response) {
//
//        request.getSession().removeAttribute(CommonConstant.Session.AUTHENTICATION);
//
//        Cookie rememberCookie = getRememberCookie(request);
//        if (rememberCookie == null) {
//            return;
//        }
//
//        CookieToken cookieToken = decodeCookie(rememberCookie.getValue());
//        afterLogout(cookieToken);
//
//        rememberCookie.setMaxAge(0);
//        rememberCookie.setValue("");
//        rememberCookie.setPath("/");
//        response.addCookie(rememberCookie);
//
//    }
//
//    /**
//     * 创建token
//     *
//     * @param user user
//     * @return CookieToken
//     */
//    protected abstract CookieToken makeToken(User user);
//
//    /**
//     * 是否有效
//     *
//     * @param user        user
//     * @param cookieToken token
//     * @return is valid signature
//     */
//    protected abstract boolean isValidSignature(User user, CookieToken cookieToken);
//
//    /**
//     * 是否过期
//     *
//     * @param cookieToken cookieToken
//     * @return result
//     */
//    protected abstract boolean isTokenExpired(CookieToken cookieToken);
//
//    /**
//     * 登出之后
//     *
//     * @param token token
//     */
//    protected void afterLogout(CookieToken token) {
//    }
//
//    /**
//     * 登录成功之后
//     *
//     * @param request     request
//     * @param response    response
//     * @param user        user
//     * @param cookieToken cookieToken
//     */
//    protected void afterLoginSuccess(HttpServletRequest request, HttpServletResponse response, User user, CookieToken cookieToken) {
//    }
//
//    /**
//     * 自动登录成功
//     *
//     * @param request  request
//     * @param response response
//     * @param user     user
//     * @param oldToken oldToken
//     * @param newToken newToken
//     */
//    protected void afterAutoLoginSuccess(HttpServletRequest request, HttpServletResponse response,
//                                         User user, CookieToken oldToken, CookieToken newToken) {
//    }
//
//    protected long calculateLoginLifetime() {
//        return System.currentTimeMillis() + expiry * 1000L;
//    }
//
//    private String encodeCookie(long expiryTime, String account, String signature) {
//        String value = expiryTime
//                + DELIMITER
//                + account
//                + DELIMITER
//                + signature;
//        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
//    }
//
//    private CookieToken decodeCookie(String cookieValue) {
//
//        String cookies = new String(Base64.getDecoder().decode(cookieValue), StandardCharsets.UTF_8);
//        String[] cookiesArr = cookies.split(DELIMITER);
//        String[] tokens = StringUtils.delimitedListToStringArray(cookies, DELIMITER);
//
//        if (cookiesArr.length != COOKIE_LENGTH) {
//            String msg = String.format("[%s] illegal cookie, parse error", cookieValue);
//            log.error(msg);
//            throw new CookieParseException(msg);
//        }
//
//        return new CookieToken(Long.parseLong(tokens[0]), tokens[1], tokens[2]);
//    }
//
//    private Cookie getRememberCookie(HttpServletRequest request) {
//
//        Cookie[] cookies = request.getCookies();
//        if (cookies == null) {
//            return null;
//        }
//
//        for (Cookie cookie : cookies) {
//            if (rememberKey.equals(cookie.getName())) {
//                return cookie;
//            }
//        }
//
//        return null;
//    }
//
//    /**
//     * 设置记住登录 token
//     *
//     * @param user     user
//     * @param response response
//     * @return cookie token
//     */
//    private CookieToken setRememberToken(User user, HttpServletResponse response) {
//
//        CookieToken newToken = makeToken(user);
//
//        String encodeCookie = encodeCookie(newToken.getExpiryTime(), user.getAccount(), newToken.getSignature());
//        Cookie cookie = new Cookie(rememberKey, encodeCookie);
//        cookie.setHttpOnly(true);
//        cookie.setMaxAge(getExpiry());
//        cookie.setPath("/");
//        response.addCookie(cookie);
//
//        return newToken;
//    }
//
//    private void setLoginStatus(Authentication authentication, HttpServletRequest request) {
//        request.getSession().setAttribute(CommonConstant.Session.AUTHENTICATION, authentication);
//    }
//
//    public void setExpiry(int expiry) {
//        this.expiry = expiry;
//    }
//
//    public void setRememberKey(String rememberKey) {
//        this.rememberKey = rememberKey;
//    }
//
//}
//
