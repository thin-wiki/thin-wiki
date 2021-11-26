package wiki.thin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wiki.thin.entity.StorageFile;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StorageFileMapper extends BaseMapper<StorageFile> {

    /**
     * find
     *
     * @param storageId storageId
     * @return files
     */
    @Select("select * from storage_file where storage_id = #{storageId}")
    List<StorageFile> findAllByStorageId(@Param("storageId") Long storageId);

}