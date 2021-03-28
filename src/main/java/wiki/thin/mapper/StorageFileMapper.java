package wiki.thin.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cache.decorators.SoftCache;
import org.springframework.stereotype.Repository;
import wiki.thin.entity.StorageFile;

import java.util.Optional;

/**
 * @author Beldon
 */
@Mapper
@Repository
@CacheNamespace(eviction = SoftCache.class)
public interface StorageFileMapper {

    /**
     * find
     *
     * @param id id
     * @return StorageFile
     */
    @Select("select * from storage_file where id = #{id}")
    Optional<StorageFile> findById(@Param("id") Long id);

    /**
     * insert
     *
     * @param file file
     * @return insert count
     */
    int insertSelective(StorageFile file);
}
