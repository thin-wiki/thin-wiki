package wiki.thin.module.storage;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * @author Beldon
 */
public enum StorageType implements IEnum<Integer> {
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

    private final int value;

    StorageType(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
