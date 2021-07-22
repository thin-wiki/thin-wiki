package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import wiki.thin.entity.PostViewHistory;

/**
 * @author beldon
 */
public interface PostViewHistoryAutoRepo extends ReactiveSortingRepository<PostViewHistory,Long> {
}
