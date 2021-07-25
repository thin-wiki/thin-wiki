package wiki.thin.repo;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import wiki.thin.entity.Post;

/**
 * @author beldon
 */
public interface PostAutoRepo extends ReactiveSortingRepository<Post, Long> {
    Flux<Post> findByStatus(Integer status, Sort sort);

    Mono<Void> deleteByStatus(Integer status);

    Mono<Void> deleteByIdAndStatus(Long id, Integer status);
}
