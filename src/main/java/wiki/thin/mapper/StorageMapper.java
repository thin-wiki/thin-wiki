package wiki.thin.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.SoftCache;
import org.springframework.stereotype.Repository;
import wiki.thin.entity.Storage;
import wiki.thin.storage.StorageWorkType;

import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@Mapper
@Repository
@CacheNamespace(eviction = SoftCache.class)
public interface StorageMapper {
    /**
     * find
     *
     * @return Storage
     */
    @Select("select * from storage order by created_date")
    List<Storage> findAll();

    /**
     * find
     *
     * @param workType workType
     * @param writable writable
     * @return Storage
     */
    @Select("select * from storage where work_type = #{workType} and writable = #{writable}")
    List<Storage> findByWorkTypeAndWritable(@Param("workType") StorageWorkType workType, @Param("writable") Boolean writable);

    /**
     * find
     *
     * @param mainStorageId mainStorageId
     * @return Storage
     */
    @Select("select * from storage where main_storage_id = #{mainStorageId}")
    List<Storage> findByMainStorageId(@Param("mainStorageId") Long mainStorageId);

    /**
     * find
     *
     * @param id id
     * @return storage
     */
    @Select("select * from storage where id = #{id}")
    Optional<Storage> findById(@Param("id") Long id);

    /**
     * insert
     *
     * @param storage storage
     * @return insert count
     */
    int insertSelective(Storage storage);

    /**
     * update
     *
     * @param storage storage
     * @return update count
     */
    int updateByIdSelective(Storage storage);

    /**
     * delete
     *
     * @param id id
     * @return delete count
     */
    @Update("delete from storage where id = #{id}")
    int delete(@Param("id") Long id);
}
