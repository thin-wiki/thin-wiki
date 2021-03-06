package wiki.thin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import wiki.thin.entity.AppConfig;

import java.util.Optional;

/**
 * @author Beldon
 */
@Mapper
@Repository
public interface AppConfigMapper {

    /**
     * find type
     *
     * @param type type
     * @param key  key
     * @return AppConfig
     */
    @Select("select * from app_config where `type` = #{type} and `key` = #{key}")
    Optional<AppConfig> findByTypeAndKey(@Param("type") String type, @Param("key") String key);

    /**
     * insert by selective
     *
     * @param config config
     * @return insert count
     */
    int insertSelective(AppConfig config);

    /**
     * update
     *
     * @param config config
     * @return update count
     */
    int updateByIdSelective(AppConfig config);

    /**
     * update
     *
     * @param type           type
     * @param key            key
     * @param value          value
     * @param lastModifiedBy lastModifiedBy
     * @return update count
     */
    int updateValue(@Param("type") String type, @Param("key") String key, @Param("value") String value, @Param("lastModifiedBy") Long lastModifiedBy);
}
