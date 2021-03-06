package wiki.thin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import wiki.thin.entity.User;

import java.util.Date;
import java.util.Optional;

/**
 * @author Beldon
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * find
     *
     * @param account account
     * @return User
     */
    @Select("select * from user where account = #{account}")
    Optional<User> findByAccount(@Param("account") String account);

    /**
     * update
     *
     * @param userId        userId
     * @param lastLoginTime lastLoginTime
     * @return update count
     */
    @Update("update user set last_login_time = #{lastLoginTime} where id = #{userId}")
    int updateLastLoginTime(@Param("userId") Long userId, @Param("lastLoginTime") Date lastLoginTime);
}
