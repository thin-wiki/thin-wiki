package wiki.thin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wiki.thin.security.annotation.NeedAuth;

/**
 * @author Beldon
 */
@Controller
@RequestMapping("/config")
public class AppConfigController extends BaseController {

    @GetMapping
    @NeedAuth
    public String index() {
        return "config/index";
    }
}
