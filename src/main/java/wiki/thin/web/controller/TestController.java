package wiki.thin.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.thin.backup.MySqlService;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {
    private final MySqlService mySqlService;

    @GetMapping
    public Object test() {
        return mySqlService.getDataInsertSql("post_column");
    }
}
