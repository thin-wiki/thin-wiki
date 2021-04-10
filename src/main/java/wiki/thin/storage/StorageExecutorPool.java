package wiki.thin.storage;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class StorageExecutorPool {
    private static final int POOL_SIZE = 5;
    private static final ThreadPoolExecutor POOL;

    static {
        POOL = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE,
                1L, TimeUnit.MINUTES, new LinkedBlockingQueue<>(),
                new ThreadFactory() {
                    private final AtomicInteger threadNumber = new AtomicInteger(1);

                    @Override
                    public Thread newThread(Runnable r) {
                        final Thread thread = new Thread(r, "storage" + threadNumber.getAndIncrement());
                        thread.setDaemon(true);
                        return thread;
                    }
                });
        POOL.allowCoreThreadTimeOut(true);
    }

    public static Future<?> submit(Runnable task) {
        return POOL.submit(task);
    }
}
