package wiki.thin.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.r2dbc.core.FetchSpec;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import wiki.thin.backup.MySqlService;

import java.util.Map;

@RestController
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {
    private final MySqlService mySqlService;

    @GetMapping
    public Flux<Map<String, Object>> test() {
        return mySqlService.getDataInsertSql("post_column");
    }
}
