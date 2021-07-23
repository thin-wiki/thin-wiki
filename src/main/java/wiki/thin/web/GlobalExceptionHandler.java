package wiki.thin.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import wiki.thin.exception.NoLoginException;

/**
 * @author Beldon
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class, NoLoginException.class})
    public void errorHandler(Exception e) {
        log.error("error", e);
    }

}
