package wiki.thin.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.SoftCache;
import org.springframework.stereotype.Repository;
import wiki.thin.entity.GithubStorage;

import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@Mapper
@Repository
@CacheNamespace(eviction = SoftCache.class)
public interface GithubStorageMapper {

    /**
     * find
     *
     * @return GithubStorage
     */
    @Select("select * from github_storage")
    List<GithubStorage> findAll();

    /**
     * find
     *
     * @param id id
     * @return GithubStorage
     */
    @Select("select * from github_storage where id = #{id}")
    Optional<GithubStorage> findById(@Param("id") Long id);

    /**
     * insert
     *
     * @param githubStorage githubStorage
     * @return insert count
     */
    int insertSelective(GithubStorage githubStorage);

    /**
     * update
     *
     * @param githubStorage githubStorage
     * @return update count
     */
    int updateByIdSelective(GithubStorage githubStorage);

    /**
     * delte
     *
     * @param id id
     * @return delete count
     */
    @Update("delete from github_storage where id = #{id}")
    int delete(@Param("id") Long id);
}
