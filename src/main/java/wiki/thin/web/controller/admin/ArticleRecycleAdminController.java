package wiki.thin.web.controller.admin;

import org.springframework.web.bind.annotation.*;
import wiki.thin.constant.CommonConstant;
import wiki.thin.entity.Article;
import wiki.thin.mapper.ArticleMapper;
import wiki.thin.web.vo.ArticleDetailVO;
import wiki.thin.web.vo.ResponseVO;

import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@RequestMapping("/api/admin/article/recycle")
@RestController
public class ArticleRecycleAdminController {

    private final ArticleMapper articleMapper;

    public ArticleRecycleAdminController(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    /**
     * 获取回收站数据
     *
     * @return
     */
    @GetMapping
    public ResponseVO list() {
        final List<ArticleDetailVO> articleLists = articleMapper.findByStatus(CommonConstant.STATUS_RECYCLE);
        return ResponseVO.successWithData(articleLists);
    }

    /**
     * @return response
     */
    @DeleteMapping("/clean")
    public ResponseVO clean() {
        articleMapper.deleteByStatus(CommonConstant.STATUS_RECYCLE);
        return ResponseVO.success();
    }

    /**
     * @return response
     */
    @DeleteMapping("/{articleId}")
    public ResponseVO delete(@PathVariable("articleId") Long articleId) {
        articleMapper.deleteByIdAndStatus(articleId, CommonConstant.STATUS_RECYCLE);
        return ResponseVO.success();
    }

    /**
     * @return response
     */
    @PutMapping("/{articleId}/restore")
    public ResponseVO restore(@PathVariable("articleId") Long articleId) {
        final Optional<Article> articleOptional = articleMapper.findById(articleId);
        if (articleOptional.isEmpty()) {
            return ResponseVO.error("找不到指定类目");
        }
        final var article = articleOptional.get();
        if (!CommonConstant.DEFAULT_PARENT_ID.equals(article.getParentId())) {
            final Optional<Article> parentOptional = articleMapper.findById(article.getParentId());
            if (parentOptional.isEmpty()) {
                articleMapper.updatePid(articleId, CommonConstant.DEFAULT_PARENT_ID);
            }
        }

        articleMapper.updateStatus(articleId, CommonConstant.STATUS_NORMAL);

        return ResponseVO.success();
    }

}
