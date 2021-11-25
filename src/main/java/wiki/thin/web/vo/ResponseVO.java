package wiki.thin.web.vo;

import lombok.Data;

/**
 * @author Beldon.
 */
@Data
public class ResponseVO<T> {
    private final Integer code;

    private final String msg;

    private final T data;

    private ResponseVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseVO<Object> success() {
        return success(null);
    }

    public static ResponseVO<Object> success(String msg) {
        return success(msg, null);
    }

    public static <T> ResponseVO<T> success(String msg, T data) {
        return response(0, msg, data);
    }

    public static <T> ResponseVO<T> successWithData(T data) {
        return response(0, null, data);
    }

    public static ResponseVO<Object> error(String msg) {
        return response(1, msg, null);
    }

    public static ResponseVO<Object> error(Integer code, String msg) {
        return response(code, msg, null);
    }

    public static <T> ResponseVO<T> response(int code, String msg, T data) {
        return new ResponseVO<>(code, msg, data);
    }
}
