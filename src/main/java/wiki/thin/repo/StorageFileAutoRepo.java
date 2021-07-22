package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import wiki.thin.entity.StorageFile;

/**
 * @author beldon
 */
public interface StorageFileAutoRepo extends ReactiveSortingRepository<StorageFile, Long> {
}
