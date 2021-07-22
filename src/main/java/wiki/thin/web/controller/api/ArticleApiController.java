//package wiki.thin.web.controller.api;
//
//import org.springframework.web.bind.annotation.*;
//import wiki.thin.constant.CommonConstant;
//import wiki.thin.constant.enums.SharableEnum;
//import wiki.thin.entity.Post;
//import wiki.thin.mapper.ArticleColumnMapper;
//import wiki.thin.mapper.ArticleMapper;
//import wiki.thin.service.ArticleHistoryService;
//import wiki.thin.service.ArticleSearchService;
//import wiki.thin.web.vo.ArticleModifyVO;
//import wiki.thin.web.vo.ArticleVO;
//import wiki.thin.web.vo.ResponseVO;
//
//import javax.validation.Valid;
//
///**
// * @author Beldon
// */
//@RequestMapping("/api/article")
//@RestController
//public class ArticleApiController {
//
//    private final ArticleMapper articleMapper;
//    private final ArticleColumnMapper articleColumnMapper;
//    private final ArticleSearchService articleSearchService;
//    private final ArticleHistoryService articleHistoryService;
//
//    public ArticleApiController(ArticleMapper articleMapper, ArticleColumnMapper articleColumnMapper,
//                                ArticleSearchService articleSearchService,
//                                ArticleHistoryService articleHistoryService) {
//        this.articleMapper = articleMapper;
//        this.articleColumnMapper = articleColumnMapper;
//        this.articleSearchService = articleSearchService;
//        this.articleHistoryService = articleHistoryService;
//    }
//
//    /**
//     * 添加
//     *
//     * @param articleVO article
//     * @return response
//     */
//    @PostMapping
//    public ResponseVO saveArticle(@RequestBody @Valid ArticleVO articleVO) {
//
//        final var columnPath = articleVO.getColumnPath();
//        final var parentId = articleVO.getParentId();
//
//        if (columnPath == null && parentId == null) {
//            return ResponseVO.error("column path 和 parentId 不能同时为空");
//        }
//
//        Long realParentId = null;
//        Long realColumnId = null;
//
//        if (parentId != null) {
//            final var articleOptional = articleMapper.findById(parentId);
//            if (articleOptional.isPresent()) {
//                realParentId = parentId;
//                realColumnId = articleOptional.get().getColumnId();
//            }
//        }
//
//        if (realColumnId == null) {
//            final var columnOptional = articleColumnMapper.findByPath(columnPath);
//            if (columnOptional.isPresent()) {
//                realParentId = 0L;
//                realColumnId = columnOptional.get().getId();
//            }
//        }
//
//        if (realColumnId == null) {
//            return ResponseVO.error("找不到指定 column");
//        }
//
//        var article = new Post();
//        article.setTitle(articleVO.getTitle());
//        article.setContent(articleVO.getContent());
//        article.setParentId(realParentId);
//        article.setColumnId(realColumnId);
//        article.setSharable(SharableEnum.INHERITED);
//        articleMapper.insertSelective(article);
//
//
//        articleSearchService.index(article.getId());
//        articleHistoryService.saveArticleHistory(article.getId());
//
//        return ResponseVO.successWithData(article.getId().toString());
//    }
//
//    @PutMapping("/{articleId}")
//    public ResponseVO updateArticle(@PathVariable Long articleId, @RequestBody @Valid ArticleModifyVO articleVO) {
//        final var articleOptional = articleMapper.findById(articleId);
//        if (articleOptional.isEmpty()) {
//            return ResponseVO.error("找不到指定记录");
//        }
//
//        final var article = articleOptional.get();
//        article.setTitle(articleVO.getTitle());
//        article.setContent(articleVO.getContent());
//        articleMapper.updateByIdSelective(article);
//
//        articleSearchService.index(article.getId());
//        articleHistoryService.saveArticleHistory(article.getId());
//
//        return ResponseVO.successWithData(article.getId());
//    }
//
//    @PutMapping("/{articleId}/pid")
//    public ResponseVO updateParentId(@PathVariable Long articleId, @RequestParam("parentId") Long parentId) {
//        articleMapper.updatePid(articleId, parentId);
//        return ResponseVO.success();
//    }
//
//    @PutMapping("/{articleId}/share")
//    public ResponseVO updateSharable(@PathVariable Long articleId, @RequestParam("shareable") SharableEnum sharable) {
//        articleMapper.updateSharable(articleId, sharable);
//
//        articleSearchService.index(articleId);
//
//        return ResponseVO.success();
//    }
//
//    @DeleteMapping("/{articleId}")
//    public ResponseVO recycle(@PathVariable Long articleId) {
//        final var articleOptional = articleMapper.findById(articleId);
//        if (articleOptional.isEmpty()) {
//            return ResponseVO.error("找不到指定记录");
//        }
//
//        articleMapper.updateStatus(articleId, CommonConstant.STATUS_RECYCLE);
//
//        articleSearchService.delete(articleId);
//
//        return ResponseVO.success();
//    }
//}
