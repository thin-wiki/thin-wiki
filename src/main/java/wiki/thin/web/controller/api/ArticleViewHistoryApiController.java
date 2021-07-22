//package wiki.thin.web.controller.api;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import wiki.thin.entity.PostColumn;
//import wiki.thin.entity.PostViewHistory;
//import wiki.thin.mapper.ArticleColumnMapper;
//import wiki.thin.mapper.ArticleViewHistoryMapper;
//import wiki.thin.web.controller.BaseController;
//import wiki.thin.web.vo.ArticleViewHistoryVO;
//import wiki.thin.web.vo.ResponseVO;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
///**
// * @author Beldon
// */
//@RequestMapping("/api/article/view/history")
//@RestController
//public class ArticleViewHistoryApiController extends BaseController {
//
//    private final ArticleViewHistoryMapper articleViewHistoryMapper;
//    private final ArticleColumnMapper articleColumnMapper;
//
//    public ArticleViewHistoryApiController(ArticleViewHistoryMapper articleViewHistoryMapper, ArticleColumnMapper articleColumnMapper) {
//        this.articleViewHistoryMapper = articleViewHistoryMapper;
//        this.articleColumnMapper = articleColumnMapper;
//    }
//
//    @GetMapping
//    public ResponseVO list() {
//        final List<PostViewHistory> data = articleViewHistoryMapper.findByUserId(currentUserId());
//        List<ArticleViewHistoryVO> returnData = new ArrayList<>();
//        for (PostViewHistory datum : data) {
//        }
//        return ResponseVO.successWithData(returnData);
//    }
//
//    public String columnPath(Long columnId) {
//        final Optional<PostColumn> columnOptional = articleColumnMapper.findById(columnId);
//        if (columnOptional.isPresent()) {
//            return columnOptional.get().getPath();
//        }
//        return "";
//    }
//}
