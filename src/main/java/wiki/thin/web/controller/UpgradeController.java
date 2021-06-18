package wiki.thin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author beldon
 */
@Controller
@RequestMapping("/upgrade")
@Deprecated
public class UpgradeController extends BaseController {

    @GetMapping
    public String index() {
        return "upgrade/index";
    }
}
