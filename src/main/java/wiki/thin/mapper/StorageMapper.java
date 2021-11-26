package wiki.thin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wiki.thin.entity.Storage;
import wiki.thin.storage.StorageWorkType;

import java.util.List;

@Mapper
public interface StorageMapper extends BaseMapper<Storage> {
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
}