package wiki.thin.constant.enums;

import wiki.thin.common.BaseCodeEnum;

/**
 * 共享类型
 *
 * @author Beldon
 */
public enum SharableEnum implements BaseCodeEnum {

    /**
     * 共享的
     */
    SHAREABLE(0),

    /**
     * 私有的
     */
    PRIVATE(1),

    /**
     * 继承的
     */
    INHERITED(2);

    private final int code;

    SharableEnum(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }

    public static SharableEnum fromCode(int code) {
        for (SharableEnum value : values()) {
            if (code == value.code) {
                return value;
            }
        }
        return null;
    }
}
