package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import wiki.thin.entity.LocalStorage;

/**
 * @author beldon
 */
public interface LocalStorageAutoRepo extends ReactiveSortingRepository<LocalStorage, Long> {
}
