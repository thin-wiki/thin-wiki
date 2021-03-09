package wiki.thin.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import wiki.thin.exception.UnexpectedException;

import java.io.IOException;

/**
 * @author Beldon
 */
@Slf4j
public class JsonUtils {
    public static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    public static String toJsonString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj.getClass() == String.class) {
            return (String) obj;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("json序列化出错：" + obj, e);
            return null;
        }
    }

    public static <T> T parse(String json, Class<T> tClass) {
        try {
            return OBJECT_MAPPER.readValue(json, tClass);
        } catch (IOException e) {
            log.error("json解析出错：" + json, e);
            throw new UnexpectedException("json解析出错：", e);
        }
    }

}
