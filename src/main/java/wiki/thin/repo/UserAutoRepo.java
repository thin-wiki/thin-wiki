package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;
import wiki.thin.entity.User;

/**
 * @author beldon
 */
public interface UserAutoRepo extends ReactiveSortingRepository<User, Long> {
    Mono<User> findByAccount(String account);
}
