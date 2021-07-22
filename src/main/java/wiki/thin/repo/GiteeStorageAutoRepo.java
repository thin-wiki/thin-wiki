package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import wiki.thin.entity.GiteeStorage;

/**
 * @author beldon
 */
public interface GiteeStorageAutoRepo extends ReactiveSortingRepository<GiteeStorage, Long> {
}
