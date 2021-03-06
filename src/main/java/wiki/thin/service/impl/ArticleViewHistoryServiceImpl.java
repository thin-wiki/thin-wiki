package wiki.thin.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import wiki.thin.entity.ArticleViewHistory;
import wiki.thin.mapper.ArticleViewHistoryMapper;
import wiki.thin.service.ArticleViewHistoryService;

import java.util.Optional;

/**
 * @author Beldon
 */
@Service
public class ArticleViewHistoryServiceImpl implements ArticleViewHistoryService {

    private final ArticleViewHistoryMapper articleViewHistoryMapper;

    public ArticleViewHistoryServiceImpl(ArticleViewHistoryMapper articleViewHistoryMapper) {
        this.articleViewHistoryMapper = articleViewHistoryMapper;
    }

    @Async
    @Override
    public void addHistory(Long userId, Long articleId) {

        Optional<ArticleViewHistory> historyOptional = articleViewHistoryMapper.findByCreatedByAndArticleId(userId, articleId);
        if (historyOptional.isEmpty()) {
            ArticleViewHistory history = new ArticleViewHistory();
            history.setArticleId(articleId);
            history.setCreatedBy(userId);
            articleViewHistoryMapper.insertSelective(history);
        }
        // articleViewHistoryMapper.clearMore(userId, 11, 1000);
    }
}
