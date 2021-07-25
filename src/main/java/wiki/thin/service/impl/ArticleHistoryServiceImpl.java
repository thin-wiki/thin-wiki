package wiki.thin.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import wiki.thin.repo.PostAutoRepo;
import wiki.thin.repo.PostHistoryAutoRepo;
import wiki.thin.service.ArticleHistoryService;

/**
 * @author beldon
 */
@Service
@AllArgsConstructor
public class ArticleHistoryServiceImpl implements ArticleHistoryService {

    private final PostAutoRepo postAutoRepo;
    private final PostHistoryAutoRepo postHistoryAutoRepo;


    @Override
    public void saveArticleHistory(Long articleId) {
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
    }
}
