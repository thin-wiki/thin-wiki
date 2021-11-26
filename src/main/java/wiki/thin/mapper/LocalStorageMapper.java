package wiki.thin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;import org.apache.ibatis.annotations.Update;import wiki.thin.entity.LocalStorage;import java.util.List;import java.util.Optional;

@Mapper
public interface LocalStorageMapper extends BaseMapper<LocalStorage> {
    /**
     * find all
     *
     * @return LocalStorage
     */
    @Select("select * from local_storage")
    List<LocalStorage> findAll();

    /**
     * find
     *
     * @param id id
     * @return LocalStorage
     */
    @Select("select * from local_storage where id = #{id}")
    Optional<LocalStorage> findById(@Param("id") Long id);
}