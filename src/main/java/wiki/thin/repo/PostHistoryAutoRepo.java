package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import wiki.thin.entity.PostHistory;

/**
 * @author beldon
 */
public interface PostHistoryAutoRepo extends ReactiveSortingRepository<PostHistory,Long> {
}
