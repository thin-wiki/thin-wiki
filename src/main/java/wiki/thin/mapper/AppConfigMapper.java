package wiki.thin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;import wiki.thin.entity.AppConfig;import java.util.Optional;

@Mapper
public interface AppConfigMapper extends BaseMapper<AppConfig> {
    /**
     * find type
     *
     * @param type type
     * @param key  key
     * @return AppConfig
     */
    @Select("select * from app_config where `type` = #{type} and `key` = #{key}")
    Optional<AppConfig> findByTypeAndKey(@Param("type") String type, @Param("key") String key);
}