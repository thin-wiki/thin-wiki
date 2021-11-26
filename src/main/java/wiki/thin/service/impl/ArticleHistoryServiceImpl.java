package wiki.thin.service.impl;

import org.springframework.stereotype.Service;
import wiki.thin.entity.Article;
import wiki.thin.entity.ArticleHistory;
import wiki.thin.mapper.ArticleHistoryMapper;
import wiki.thin.mapper.ArticleMapper;
import wiki.thin.service.ArticleHistoryService;

import java.util.Optional;

/**
 * @author beldon
 */
@Service
public class ArticleHistoryServiceImpl implements ArticleHistoryService {

    private final ArticleMapper articleMapper;
    private final ArticleHistoryMapper articleHistoryMapper;

    public ArticleHistoryServiceImpl(ArticleMapper articleMapper, ArticleHistoryMapper articleHistoryMapper) {
        this.articleMapper = articleMapper;
        this.articleHistoryMapper = articleHistoryMapper;
    }

    @Override
    public void saveArticleHistory(Long articleId) {
        final Optional<Article> articleOptional = articleMapper.findById(articleId);
        if (articleOptional.isPresent()) {
            final Article article = articleOptional.get();
            ArticleHistory history = new ArticleHistory();
            history.setArticleId(article.getId());
            history.setTitle(article.getTitle());
            history.setContent(article.getContent());
            history.setParentId(article.getParentId());
            history.setColumnId(article.getColumnId());
            history.setSharable(article.getSharable());
            history.setVersion(article.getVersion());
            history.setModifiedBy(article.getLastModifiedBy());
            history.setModifiedDate(article.getLastModifiedDate());
            articleHistoryMapper.insert(history);
        }
    }
}
