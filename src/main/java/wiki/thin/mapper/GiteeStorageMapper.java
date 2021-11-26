package wiki.thin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wiki.thin.entity.GiteeStorage;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GiteeStorageMapper extends BaseMapper<GiteeStorage> {
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
}