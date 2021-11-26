package wiki.thin.module.backup;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import wiki.thin.common.mybatis.MybatisModifyCountInterceptor;
import wiki.thin.common.properties.AppBackupProperties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Beldon
 */
@Service
@Slf4j
public class AutoBackupManager implements MybatisModifyCountInterceptor.CountCallback {
    private static final int MIN_TASK_DELAY = 1;
    private static final ScheduledExecutorService POOL = new ScheduledThreadPoolExecutor(1, new TaskThreadFactory());

    private final List<BackupStrategy> backupStrategies = new ArrayList<>();
    private final AtomicInteger modifyCounter = new AtomicInteger(0);
    private final AtomicLong earliestModifiedTime = new AtomicLong(0);
    private final BackupService backupService;
    private final int retainFiles;

    private ScheduledFuture<?> currentTask;
    private BackupStrategy currentStrategy;

    public AutoBackupManager(BackupService backupService, AppBackupProperties appBackupProperties) {
        this.backupService = backupService;
        this.retainFiles = appBackupProperties.getRetainFiles();
        if (CollectionUtils.isEmpty(appBackupProperties.getStrategies())) {
            backupStrategies.addAll(getDefaultStrategies());
        } else {
            backupStrategies.addAll(appBackupProperties.getStrategies());
        }
        Collections.sort(backupStrategies);
    }

    @Override
    public void countModify(SqlCommandType commandType) {

        if (earliestModifiedTime.get() == 0) {
            earliestModifiedTime.set(System.currentTimeMillis());
        }

        modifyCounter.incrementAndGet();

        final BackupStrategy backupStrategy = chooseStrategy();
        if (!backupStrategy.equals(currentStrategy)) {
            log.info("change backup strategy [{}]", backupStrategy);

            currentStrategy = backupStrategy;
            cancelCurrentTask();

            currentTask = POOL.schedule(() -> {
                log.info("auto backup data");
                initData();
                try {
                    backupService.backup(retainFiles);
                } catch (IOException e) {
                    log.error("database backup fail", e);
                }
            }, getDelaySeconds(), TimeUnit.SECONDS);

        }
    }

    private BackupStrategy chooseStrategy() {
        for (BackupStrategy backupStrategy : backupStrategies) {
            if (modifyCounter.get() >= backupStrategy.getModifyCount()) {
                return backupStrategy;
            }
        }
        return backupStrategies.get(backupStrategies.size() - 1);
    }

    private void cancelCurrentTask() {
        if (currentTask != null) {
            currentTask.cancel(true);
        }
    }

    private long getDelaySeconds() {
        long delay = currentStrategy.toSecond() - (System.currentTimeMillis() - earliestModifiedTime.get()) / 1000;
        if (delay < MIN_TASK_DELAY) {
            delay = MIN_TASK_DELAY;
        }
        return delay;
    }

    private void initData() {
        earliestModifiedTime.set(0);
        modifyCounter.set(0);
        currentTask = null;
        currentStrategy = null;
    }


    private static class TaskThreadFactory implements ThreadFactory {
        private static final String PREFIX_NAME = "data_auto_back_";
        private final AtomicInteger counter = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, PREFIX_NAME + counter.getAndIncrement());
            thread.setDaemon(false);
            return thread;
        }
    }

    private List<BackupStrategy> getDefaultStrategies() {
        List<BackupStrategy> strategies = new ArrayList<>();
        strategies.add(new BackupStrategy(1, 1, TimeUnit.DAYS));
        strategies.add(new BackupStrategy(10, 12, TimeUnit.HOURS));
        strategies.add(new BackupStrategy(20, 6, TimeUnit.HOURS));
        strategies.add(new BackupStrategy(50, 1, TimeUnit.HOURS));
        return strategies;
    }
}
