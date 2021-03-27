package wiki.thin.constant.enums;

import lombok.Getter;

/**
 * @author beldon
 */
@Getter
public enum ResourceBaseUrlType {
    /**
     * local
     */
    LOCAL("local"),
    /**
     * jsdelivr
     */
    JSDELIVR("jsdelivr"),
    /**
     * custom
     */
    CUSTOM("custom");

    private final String type;

    ResourceBaseUrlType(String type) {
        this.type = type;
    }

}
