package wiki.thin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import wiki.thin.entity.LocalStorage;

import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@Mapper
@Repository
public interface LocalStorageMapper {
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

    /**
     * insert
     *
     * @param localStorage localStorage
     * @return insert count
     */
    int insertSelective(LocalStorage localStorage);

    /**
     * update
     *
     * @param localStorage localStorage
     * @return update count
     */
    int updateByIdSelective(LocalStorage localStorage);

    /**
     * delete
     *
     * @param id id
     * @return delete count
     */
    @Update("delete from local_storage where id = #{id}")
    int delete(@Param("id") Long id);
}
