package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;
import wiki.thin.entity.AppConfig;

/**
 * @author beldon
 */
public interface AppConfigAutoRepo extends ReactiveSortingRepository<AppConfig, Long> {
    Mono<AppConfig> findByTypeAndKey(String type, String key);
}