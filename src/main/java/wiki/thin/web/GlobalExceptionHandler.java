package wiki.thin.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wiki.thin.exception.NoAuthException;
import wiki.thin.exception.NoLoginException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Beldon
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class, NoLoginException.class})
    public void errorHandler(Exception e, HttpServletResponse response) {
        log.error("error", e);
        try {
            response.sendRedirect("/notFound");
        } catch (IOException ioException) {
            log.error("send redirect error", ioException);
        }
    }

    @ExceptionHandler(value = {NoAuthException.class})
    public String noAuthHandler() {
        return "auth_login";
    }
}
