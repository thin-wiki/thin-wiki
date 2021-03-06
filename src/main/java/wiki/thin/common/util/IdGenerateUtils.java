package wiki.thin.common.util;

/**
 * @author Beldon
 */
public class IdGenerateUtils {
    private static final long START_TIMESTAMP = 1608820163L;

    private static final long SEQUENCE_BIT = 4;

    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    private static final long TIMESTAMP_LEFT = SEQUENCE_BIT;

    private static volatile long sequence = 0L;

    private static volatile long lastTimeStamp = -1L;

    private static final Object LOCK = new Object();

    public static long getNextId() {
        synchronized (LOCK) {
            long currentTimeStamp = getNewTimeStamp();
            if (currentTimeStamp < lastTimeStamp) {
                throw new RuntimeException("上一次使用的时间戳大于当前时间，拒绝生成id");
            }
            if (currentTimeStamp == lastTimeStamp) {
                //时间相等，序列号自增
                sequence = (sequence + 1) & MAX_SEQUENCE;
                if (sequence == 0L) {
                    currentTimeStamp = getNextMill();
                }
            } else {
                sequence = 0L;
            }
            lastTimeStamp = currentTimeStamp;
            return (currentTimeStamp - START_TIMESTAMP) << TIMESTAMP_LEFT | sequence;
        }
    }

    private static long getNextMill() {
        long mill = getNewTimeStamp();
        while (mill <= lastTimeStamp) {
            mill = getNewTimeStamp();
        }
        return mill;
    }

    private static long getNewTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }
}
