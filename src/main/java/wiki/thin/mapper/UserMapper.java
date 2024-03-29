package wiki.thin.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.SoftCache;
import org.springframework.stereotype.Repository;
import wiki.thin.entity.User;

import java.util.Date;
import java.util.Optional;

/**
 * @author Beldon
 */
@Mapper
@Repository
@CacheNamespace(eviction = SoftCache.class)
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

    /**
     * update password
     *
     * @param userId      userId
     * @param newPassword newPassword
     * @return update count
     */
    @Update("update user set password = #{newPassword} where id = #{userId}")
    int updatePassword(@Param("userId") Long userId, @Param("newPassword") String newPassword);
}
