package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import wiki.thin.entity.Storage;

/**
 * @author beldon
 */
public interface StorageAutoRepo extends ReactiveSortingRepository<Storage, Long> {
}
