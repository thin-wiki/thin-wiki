package wiki.thin.backup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author Beldon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackupStrategy implements Comparable<BackupStrategy> {
    /**
     * 修改数据量
     */
    private int modifyCount;

    /**
     * 保存相隔时间
     */
    private long duration;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public long toSecond() {
        return timeUnit.toSeconds(duration);
    }

    @Override
    public int compareTo(BackupStrategy o) {
        return (int) (timeUnit.toMillis(duration) - o.timeUnit.toMillis(o.duration));
    }
}
