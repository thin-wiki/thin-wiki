package wiki.thin.web.controller;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wiki.thin.common.bean.Page;
import wiki.thin.module.search.bean.ArticleSearch;
import wiki.thin.service.ArticleSearchService;
import wiki.thin.web.vo.ResponseVO;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author beldon
 */
@Controller
@RequestMapping("/search")
public class SearchController extends BaseController {

    private final ArticleSearchService articleSearchService;

    public SearchController(ArticleSearchService articleSearchService) {
        this.articleSearchService = articleSearchService;
    }

    @GetMapping("/reindex")
    @ResponseBody
    public ResponseVO reindex() {
        articleSearchService.reBuildIndex();
        return ResponseVO.success();
    }

    @GetMapping
    public String search(@RequestParam String keyword, @RequestParam(defaultValue = "1") Integer currentPage,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         Model model) throws IOException, ParseException {

        final Page<ArticleSearch> page;
        String segmentKeyword = keyword;
        if (!StringUtils.hasText(keyword)) {
            page = new Page<>(currentPage, pageSize, 0, new ArrayList<>());
        } else {
            if (isLogin()) {
                page = articleSearchService.searchAll(keyword, currentPage, pageSize);
            } else {
                page = articleSearchService.searchSharableAll(segmentKeyword, currentPage, pageSize);
            }
        }

        model.addAttribute("segmentKeyword", segmentKeyword);
        model.addAttribute("keyword", keyword);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("page", page);

        return "search";
    }

}
