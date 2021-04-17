package wiki.thin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import wiki.thin.security.annotation.NeedLogin;

/**
 * @author Beldon
 */
@Controller
public class IndexController extends BaseController {

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @NeedLogin
    @GetMapping("/last-modified")
    public String lastModified() {
        return "last-modified";
    }

    @GetMapping("/notFound")
    public String notFound() {
        return "notFound";
    }
}
