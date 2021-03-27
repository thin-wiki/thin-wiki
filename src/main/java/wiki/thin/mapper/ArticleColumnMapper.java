package wiki.thin.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.SoftCache;
import org.springframework.stereotype.Repository;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.entity.ArticleColumn;
import wiki.thin.entity.mini.ArticleColumnList;
import wiki.thin.entity.mini.ArticleColumnShort;

import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@Mapper
@Repository
@CacheNamespace(eviction = SoftCache.class, flushInterval = 1000 * 60 * 60)
public interface ArticleColumnMapper {

    /**
     * find all article column
     *
     * @return article column
     */
    @Select("select * from article_column")
    List<ArticleColumn> findAll();

    /**
     * find all article column
     *
     * @return article column
     */
    @Select("select id,`path`, `title`,`sharable` from article_column")
    List<ArticleColumnList> findAllList();

    /**
     * 获取所有共享的列表
     *
     * @param sharable sharable
     * @return article column
     */
    @Select("select id,`path`, `title`,`sharable` from article_column where sharable=#{sharable}")
    List<ArticleColumnList> findSharedList(@Param("sharable") SharableEnum sharable);

    /**
     * find article column by id
     *
     * @param id id
     * @return article column
     */
    @Select("select * from article_column where id = #{id}")
    Optional<ArticleColumn> findById(@Param("id") Long id);

    /**
     * find article column by path
     *
     * @param path path
     * @return column
     */
    @Select("select * from article_column where path = #{path}")
    Optional<ArticleColumn> findByPath(@Param("path") String path);

    /**
     * find id by path
     *
     * @param path path
     * @return column id
     */
    @Select("select id,`title`,sharable from article_column where path = #{path}")
    Optional<ArticleColumnShort> findShortByPath(@Param("path") String path);

    /**
     * count article column by path
     *
     * @param path path
     * @return count
     */
    @Select("select count(*) from article_column where path = #{path}")
    int countByPath(@Param("path") String path);

    /**
     * insert
     *
     * @param column column
     * @return insert count
     */
    int insertSelective(ArticleColumn column);

    /**
     * update
     *
     * @param column column
     * @return update count
     */
    int updateByIdSelective(ArticleColumn column);

    /**
     * delete
     *
     * @param id id
     * @return delete count
     */
    @Update("delete from article_column where id = #{id}")
    int deleteById(@Param("id") Long id);

    /**
     * update sharable
     *
     * @param id       id
     * @param sharable sharable
     * @return update count
     */
    @Update("update article_column set sharable = #{sharable} where id = #{id}")
    int updateSharable(@Param("id") Long id, @Param("sharable") SharableEnum sharable);

    /**
     * find sharable by id
     *
     * @param id id
     * @return sharable
     */
    @Select("select sharable from article_column where id = #{id}")
    Optional<SharableEnum> findSharableById(@Param("id") Long id);
}
