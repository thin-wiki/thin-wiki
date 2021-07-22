//package wiki.thin.security.remember;
//
//import org.springframework.web.filter.OncePerRequestFilter;
//import wiki.thin.constant.CommonConstant;
//import wiki.thin.security.Authentication;
//import wiki.thin.security.AuthenticationContextHolder;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Optional;
//
///**
// * @author Beldon
// */
//public class RememberMeFilter extends OncePerRequestFilter {
//
//    private final RememberMeService rememberMeServices;
//
//    public RememberMeFilter(RememberMeService rememberMeServices) {
//        this.rememberMeServices = rememberMeServices;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
//            throws IOException, ServletException {
//
//        if (AuthenticationContextHolder.noAuthentication()) {
//            Optional<Authentication> authenticationOptional = rememberMeServices.autoLogin(request, response);
//            final Authentication authentication = authenticationOptional.orElse(Authentication.GUEST);
//
//            AuthenticationContextHolder.setAuthentication(authentication);
//            request.getSession().setAttribute(CommonConstant.Session.AUTHENTICATION, authentication);
//        }
//
//        chain.doFilter(request, response);
//    }
//}
