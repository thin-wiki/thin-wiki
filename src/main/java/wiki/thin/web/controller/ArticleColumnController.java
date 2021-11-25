package wiki.thin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Beldon
 */
@Controller
public class ArticleColumnController extends BaseController {

    @GetMapping({"/{columnPath}", "/{columnPath}/", "/{columnPath}/index"})
    public String column(@PathVariable String columnPath, Model model) {
        model.addAttribute("columnPath", columnPath);
        return "column/index";
    }

    @GetMapping("/column")
    public String list() {
        return "column/list";
    }

    @GetMapping("/column/new")
    public String newColumn() {
        return "column/new";
    }

    @GetMapping("/{columnPath}/edit")
    public String editColumn(@PathVariable String columnPath, Model model) {
        model.addAttribute("columnPath", columnPath);
        return "column/edit";
    }

}
