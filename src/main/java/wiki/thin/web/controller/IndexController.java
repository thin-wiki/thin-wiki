package wiki.thin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wiki.thin.common.AppSystem;

/**
 * @author Beldon
 */
@Controller
public class IndexController extends BaseController {

    @GetMapping({"", "/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/last-modified")
    public String lastModified() {
        return "last-modified";
    }

    @GetMapping("/notFound")
    public String notFound() {
        return "notFound";
    }

    @GetMapping("/restart")
    @ResponseBody
    public String restart() {
        AppSystem.restart();
        return "restart";
    }
}
