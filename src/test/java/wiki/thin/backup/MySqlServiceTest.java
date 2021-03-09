package wiki.thin.backup;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Disabled
class MySqlServiceTest {

    @Autowired
    private MySqlService mySqlService;

    @Test
    void getAllTables() {
        final List<String> wiki = mySqlService.getAllTables("thin-wiki");
        System.out.println(wiki);
    }

    @Test
    void getQueryCreateTable() {
        final String user = mySqlService.getQueryCreateTableSql("user");
        System.out.println(user);
    }

    @Test
    void getDataInsertSql() {
        System.out.println(mySqlService.getDataInsertSql("article_view_history"));

    }
}