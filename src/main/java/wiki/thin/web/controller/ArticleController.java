//package wiki.thin.web.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import wiki.thin.security.annotation.NeedLogin;
//import wiki.thin.service.ArticleViewHistoryService;
//
///**
// * @author Beldon
// */
//@Controller
//public class ArticleController extends BaseController {
//
//    private final ArticleViewHistoryService articleViewHistoryService;
//
//    public ArticleController(ArticleViewHistoryService articleViewHistoryService) {
//        this.articleViewHistoryService = articleViewHistoryService;
//    }
//
//    @GetMapping("/{columnPath}/{articleId:\\d++}")
//    public String page(@PathVariable String columnPath, @PathVariable Long articleId, Model model) {
//        model.addAttribute("columnPath", columnPath);
//        model.addAttribute("articleId", articleId);
//
//        if (isLogin()) {
//            articleViewHistoryService.addHistory(currentUserId(), articleId);
//        }
//
//        return "article/detail";
//    }
//
//    @NeedLogin
//    @GetMapping("/{columnPath}/new")
//    public String newPage(@PathVariable String columnPath, Model model) {
//        model.addAttribute("column", columnPath);
//        return "article/new";
//    }
//
//    @NeedLogin
//    @GetMapping("/{columnPath}/{articleId}/new")
//    public String newPaged(@PathVariable String columnPath, @PathVariable Long articleId, Model model) {
//        model.addAttribute("columnPath", columnPath);
//        model.addAttribute("articleId", articleId);
//        return "article/new";
//    }
//
//    @NeedLogin
//    @GetMapping("/{columnPath}/{articleId}/edit")
//    public String editPage(@PathVariable String columnPath, @PathVariable Long articleId, Model model) {
//        model.addAttribute("columnPath", columnPath);
//        model.addAttribute("articleId", articleId);
//        return "article/edit";
//    }
//}
