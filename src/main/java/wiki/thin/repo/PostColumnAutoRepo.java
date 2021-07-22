package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import wiki.thin.entity.PostColumn;

/**
 * @author beldon
 */
public interface PostColumnAutoRepo extends ReactiveSortingRepository<PostColumn, Long> {
}
