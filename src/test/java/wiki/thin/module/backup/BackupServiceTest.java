package wiki.thin.module.backup;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
class BackupServiceTest {

    @Autowired
    private BackupService backupService;

    @Test
    void backup() throws Exception{
        backupService.backup();
    }

    @Test
    void list() {
    }

    @Test
    void restore() {
    }
}