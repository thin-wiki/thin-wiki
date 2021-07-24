package wiki.thin.web;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import wiki.thin.exception.NoLoginException;
import wiki.thin.web.vo.ResponseVO;

import java.util.Optional;

/**
 * @author Beldon
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public void errorHandler(Exception e) {
        log.error("error", e);
    }

    @ExceptionHandler(value = {NoLoginException.class})
    public ResponseEntity<ResponseVO> noLogin() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ResponseVO.error("未登录"));
    }

}
