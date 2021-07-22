package wiki.thin.repo;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import wiki.thin.entity.GithubStorage;

/**
 * @author beldon
 */
public interface GithubStorageAutoRepo extends ReactiveSortingRepository<GithubStorage,Long> {
}
