package wiki.thin.storage;

import wiki.thin.common.BaseCodeEnum;

/**
 * storage 工作类型
 *
 * @author Beldon
 */
public enum StorageWorkType implements BaseCodeEnum {
    /**
     * 主库
     */
    MAIN(1),
    /**
     * 备库
     */
    BACKUP(2);

    private final int code;

    StorageWorkType(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return code;
    }
}
