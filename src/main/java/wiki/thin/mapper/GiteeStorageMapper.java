package wiki.thin.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.SoftCache;
import org.springframework.stereotype.Repository;
import wiki.thin.entity.GiteeStorage;

import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@Mapper
@Repository
@CacheNamespace(eviction = SoftCache.class)
public interface GiteeStorageMapper {

    /**
     * find
     *
     * @return GiteeStorage
     */
    @Select("select * from gitee_storage")
    List<GiteeStorage> findAll();

    /**
     * find
     *
     * @param id id
     * @return GiteeStorage
     */
    @Select("select * from gitee_storage where id = #{id}")
    Optional<GiteeStorage> findById(@Param("id") Long id);

    /**
     * insert
     *
     * @param giteeStorage giteeStorage
     * @return insert count
     */
    int insertSelective(GiteeStorage giteeStorage);

    /**
     * update
     *
     * @param giteeStorage giteeStorage
     * @return update count
     */
    int updateByIdSelective(GiteeStorage giteeStorage);

    /**
     * delete
     *
     * @param id id
     * @return delete count
     */
    @Update("delete from gitee_storage where id = #{id}")
    int delete(@Param("id") Long id);
}
