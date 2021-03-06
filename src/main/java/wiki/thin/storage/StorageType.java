package wiki.thin.storage;

import wiki.thin.common.BaseCodeEnum;

/**
 * @author Beldon
 */
public enum StorageType implements BaseCodeEnum {
    /**
     * 本地文件
     */
    LOCAL(1),
    /**
     * gitee
     */
    GITEE(2),
    /**
     * github
     */
    GITHUB(3);

    private final int code;

    StorageType(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
