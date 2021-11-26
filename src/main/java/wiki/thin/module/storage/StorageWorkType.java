package wiki.thin.module.storage;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * storage 工作类型
 *
 * @author Beldon
 */
public enum StorageWorkType implements IEnum<Integer> {
    /**
     * 主库
     */
    MAIN(1),
    /**
     * 备库
     */
    BACKUP(2);

    private final int value;

    StorageWorkType(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
