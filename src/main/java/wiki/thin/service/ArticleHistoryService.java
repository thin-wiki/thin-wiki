package wiki.thin.service;

import org.springframework.scheduling.annotation.Async;

/**
 * @author beldon
 */
public interface ArticleHistoryService {

    /**
     * save article history
     *
     * @param articleId articleId
     */
    @Async
    void saveArticleHistory(Long articleId);
}
