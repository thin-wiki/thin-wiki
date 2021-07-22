//package wiki.thin.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.web.servlet.HandlerInterceptor;
//import wiki.thin.common.util.JsonUtils;
//import wiki.thin.web.vo.ResponseVO;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//
///**
// * api interceptor.
// *
// * @author Beldon
// */
//@Slf4j
//public class ApiInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        if (AuthenticationContextHolder.isLogin()) {
//            return true;
//        }
//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        final ResponseVO forbidden = ResponseVO.error(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.name());
//        final String responseData = JsonUtils.toJsonString(forbidden);
//        try {
//            final ServletOutputStream outputStream = response.getOutputStream();
//            outputStream.write(responseData.getBytes(StandardCharsets.UTF_8));
//            outputStream.flush();
//        } catch (IOException exception) {
//            log.error("write response error", exception);
//        }
//
//        return false;
//    }
//}
