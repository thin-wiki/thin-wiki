package wiki.thin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wiki.thin.entity.GithubStorage;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GithubStorageMapper extends BaseMapper<GithubStorage> {
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
}