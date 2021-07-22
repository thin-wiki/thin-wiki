package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import wiki.thin.entity.Post;

/**
 * @author beldon
 */
public interface PostAutoRepo extends ReactiveSortingRepository<Post, Long> {
}
