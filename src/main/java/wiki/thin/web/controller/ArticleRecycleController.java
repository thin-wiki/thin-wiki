package wiki.thin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import wiki.thin.security.annotation.NeedLogin;

/**
 * @author Beldon
 */
@Controller
public class ArticleRecycleController extends BaseController {

    @NeedLogin
    @GetMapping("/recycle")
    public String newPaged() {
        return "article/recycle";
    }

}
