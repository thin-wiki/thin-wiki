package wiki.thin.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import wiki.thin.exception.NoLoginException;
import wiki.thin.web.vo.ResponseVO;

/**
 * @author Beldon
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ResponseVO> errorHandler(Exception e) {
        log.error("error", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseVO.error("system error"));
    }

    @ExceptionHandler(value = {NoLoginException.class})
    public ResponseEntity<ResponseVO> noLogin() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseVO.error("未登录"));
    }


    /**
     * 参数校验异常
     *
     * @param e exception
     * @return response
     */
    @ExceptionHandler({WebExchangeBindException.class})
    public ResponseEntity<ResponseVO> methodArgumentNotValidException(Exception e) {
        if (log.isDebugEnabled()) {
            log.info(e.getMessage(), e);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseVO.error("参数异常"));
    }


}
