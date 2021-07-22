//package wiki.thin.security.remember;
//
//import wiki.thin.entity.User;
//import wiki.thin.security.Authentication;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Optional;
//
///**
// * @author Beldon
// */
//public interface RememberMeService {
//
//    /**
//     * 自动登录
//     *
//     * @param request  request
//     * @param response response
//     * @return Authentication
//     */
//    Optional<Authentication> autoLogin(HttpServletRequest request, HttpServletResponse response);
//
//    /**
//     * 普通登录
//     *
//     * @param request  request
//     * @param response response
//     * @param user     user
//     * @return Authentication
//     */
//    Optional<Authentication> login(HttpServletRequest request, HttpServletResponse response, User user);
//
//    /**
//     * 登出
//     *
//     * @param request  request
//     * @param response response
//     */
//    void logout(HttpServletRequest request, HttpServletResponse response);
//
//}
