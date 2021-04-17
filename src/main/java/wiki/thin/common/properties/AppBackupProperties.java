package wiki.thin.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import wiki.thin.backup.BackupStrategy;

import java.util.List;

/**
 * @author beldon
 */
@Component
@Data
@ConfigurationProperties(prefix = "app.backup")
public class AppBackupProperties {
    /**
     * 保留备份文件数
     */
    private Integer retainFiles = 20;
    /**
     * 自动备份策略
     */
    private List<BackupStrategy> strategies;
}
