//package wiki.thin.service.impl;
//
//import org.springframework.stereotype.Service;
//import wiki.thin.entity.Post;
//import wiki.thin.entity.PostHistory;
//import wiki.thin.mapper.ArticleHistoryMapper;
//import wiki.thin.mapper.ArticleMapper;
//import wiki.thin.service.ArticleHistoryService;
//
//import java.util.Optional;
//
///**
// * @author beldon
// */
//@Service
//public class ArticleHistoryServiceImpl implements ArticleHistoryService {
//
//    private final ArticleMapper articleMapper;
//    private final ArticleHistoryMapper articleHistoryMapper;
//
//    public ArticleHistoryServiceImpl(ArticleMapper articleMapper, ArticleHistoryMapper articleHistoryMapper) {
//        this.articleMapper = articleMapper;
//        this.articleHistoryMapper = articleHistoryMapper;
//    }
//
//    @Override
//    public void saveArticleHistory(Long articleId) {
//        final Optional<Post> articleOptional = articleMapper.findById(articleId);
//        if (articleOptional.isPresent()) {
//            final Post post = articleOptional.get();
//            PostHistory history = new PostHistory();
//            history.setArticleId(post.getId());
//            history.setTitle(post.getTitle());
//            history.setContent(post.getContent());
//            history.setParentId(post.getParentId());
//            history.setColumnId(post.getColumnId());
//            history.setSharable(post.getSharable());
//            history.setVersion(post.getVersion());
//            history.setModifiedBy(post.getLastModifiedBy());
//            history.setModifiedDate(post.getLastModifiedDate());
//            articleHistoryMapper.insertSelective(history);
//        }
//    }
//}
