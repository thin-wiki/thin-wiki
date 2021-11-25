package wiki.thin.web.controller.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.thin.mapper.ArticleColumnMapper;
import wiki.thin.mapper.ArticleMapper;
import wiki.thin.web.vo.DashboardTotalVO;
import wiki.thin.web.vo.ResponseVO;

/**
 * @author beldon
 */
@RequestMapping("/api/admin/dashboard")
@RestController
public class DashboardAdminController {

    private final ArticleColumnMapper articleColumnMapper;
    private final ArticleMapper articleMapper;

    public DashboardAdminController(ArticleColumnMapper articleColumnMapper, ArticleMapper articleMapper) {
        this.articleColumnMapper = articleColumnMapper;
        this.articleMapper = articleMapper;
    }

    @GetMapping("/total")
    public ResponseVO<DashboardTotalVO> total() {
        DashboardTotalVO totalVO = new DashboardTotalVO();
        totalVO.setColumnCount(articleColumnMapper.countAll());
        totalVO.setArticleCount(articleMapper.countAll());
        return ResponseVO.successWithData(totalVO);
    }
}
