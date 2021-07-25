package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;
import wiki.thin.entity.PostColumn;

/**
 * @author beldon
 */
public interface PostColumnAutoRepo extends ReactiveSortingRepository<PostColumn, Long> {
    Mono<PostColumn> findByPath(String path);

    Mono<Long> countByPath(String path);
}
