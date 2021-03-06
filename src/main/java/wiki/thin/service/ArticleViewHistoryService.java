package wiki.thin.service;

/**
 * @author Beldon
 */
public interface ArticleViewHistoryService {
    /**
     * add history
     *
     * @param userId    userId
     * @param articleId articleId
     */
    void addHistory(Long userId, Long articleId);
}
