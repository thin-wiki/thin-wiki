package wiki.thin.web;

import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import wiki.thin.web.vo.ResponseVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 统一异常处理基类
 *
 * @author Beldon.
 */
@RestControllerAdvice({"wiki.thin.web.controller.api", "wiki.thin.web.controller.admin"})
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalRestExceptionHandler {

    /**
     * 全局异常
     *
     * @param e exception
     * @return response
     */
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseVO defaultErrorHandler(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseVO.error("内部异常，请查看日志");
    }


    /**
     * 参数校验异常
     *
     * @param e exception
     * @return response
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class,
            MissingServletRequestParameterException.class})
    public ResponseVO methodArgumentNotValidException(Exception e) {
        if (log.isDebugEnabled()) {
            log.info(e.getMessage(), e);
        }

        //参数缺失异常
        if (e instanceof MissingServletRequestParameterException) {
            MissingServletRequestParameterException exception = (MissingServletRequestParameterException) e;
            String message = exception.getParameterName() + "不能为空";
            return ResponseVO.error(message);
        }

        List<ObjectError> allErrors = new ArrayList<>();
        if (e instanceof BindException) {
            allErrors = ((BindException) e).getBindingResult().getAllErrors();
        } else if (e instanceof MethodArgumentNotValidException) {
            allErrors = ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors();
        }
        StringBuilder errors = new StringBuilder();
        for (ObjectError allError : allErrors) {
            errors.append(allError.getDefaultMessage());
            break;
        }
        return ResponseVO.error(errors.toString());
    }

    /**
     * 请求方法不支持异常
     *
     * @param e exception
     * @return response
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseVO methodNotSupported(HttpRequestMethodNotSupportedException e) {
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        }
        String message = "不支持" + e.getMethod() + "请求访问";
        return ResponseVO.error(message);
    }

    /**
     * 请求内容不支持
     *
     * @param e exception
     * @return response
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseVO httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        if (log.isDebugEnabled()) {
            log.debug(e.getMessage(), e);
        }
        String message = "不支持'" + e.getContentType() + "'内容";
        return ResponseVO.error(message);
    }

    @ExceptionHandler(NotLoginException.class)
    public ResponseVO handlerNotLoginException(NotLoginException nle) {
        // 判断场景值，定制化异常信息
        String message = "";
        if (nle.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        } else if (nle.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (nle.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if (nle.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if (nle.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }

        // 返回给前端
        return ResponseVO.error(message);
    }

}
