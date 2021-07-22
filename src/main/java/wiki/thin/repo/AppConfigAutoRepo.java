package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import wiki.thin.entity.AppConfig;

/**
 * @author beldon
 */
public interface AppConfigAutoRepo extends ReactiveSortingRepository<AppConfig, Long> {
}
