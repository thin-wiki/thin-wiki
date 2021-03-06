package wiki.thin.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import wiki.thin.common.util.JsonUtils;
import wiki.thin.web.vo.ResponseVO;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * api interceptor.
 *
 * @author Beldon
 */
@Slf4j
public class ApiInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (AuthenticationContextHolder.isLogin()) {
            return true;
        }
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(401);
        final ResponseVO forbidden = ResponseVO.error(401, "forbidden");
        final String responseData = JsonUtils.toJsonString(forbidden);
        try {
            final ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(responseData.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        } catch (IOException e) {
            log.error("write response error", e);
        }

        return false;
    }
}
