package wiki.thin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Beldon
 */
@Controller
public class IndexController extends BaseController {

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/notFound")
    public String notFound() {
        return "notFound";
    }
}
