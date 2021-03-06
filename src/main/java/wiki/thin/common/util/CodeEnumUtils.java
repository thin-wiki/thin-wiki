package wiki.thin.common.util;

import wiki.thin.common.BaseCodeEnum;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Beldon
 */
public class CodeEnumUtils {

    private static final Map<Class<? extends Enum<?>>, Map<Integer, Object>> ENUM_MAP = new ConcurrentHashMap<>(16);

    public static <E extends Enum<?> & BaseCodeEnum> E codeOf(Class<E> enumClass, int code) {

        if (ENUM_MAP.containsKey(enumClass)) {
            final Map<Integer, Object> integerEnumMap = ENUM_MAP.get(enumClass);
            if (integerEnumMap.containsKey(code)) {
                return (E) integerEnumMap.get(code);
            }
            final E e = find(enumClass, code);
            integerEnumMap.put(code, e);
            return e;
        }
        Map<Integer, Object> integerEnumMap = new ConcurrentHashMap<>(16);
        final E e = find(enumClass, code);
        integerEnumMap.put(code, e);
        ENUM_MAP.put(enumClass, integerEnumMap);
        return e;
    }

    private static <E extends Enum<?> & BaseCodeEnum> E find(Class<E> enumClass, int code) {
        E[] enumConstants = enumClass.getEnumConstants();
        for (E e : enumConstants) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
