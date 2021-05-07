package wiki.thin.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.SoftCache;
import org.springframework.stereotype.Repository;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.entity.Article;
import wiki.thin.entity.mini.ArticleLastModifiedList;
import wiki.thin.entity.mini.ArticleList;

import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@Mapper
@Repository
@CacheNamespace(eviction = SoftCache.class)
public interface ArticleMapper {

    /**
     * find
     *
     * @param id id
     * @return article
     */
    @Select("select * from article where id = #{id}")
    Optional<Article> findById(@Param("id") Long id);

    /**
     * count
     *
     * @param columnId columnId
     * @return count
     */
    @Select("select count(*) from article where column_id = #{columnId}")
    int countByColumnId(@Param("columnId") Long columnId);

    /**
     * insert
     *
     * @param article article
     * @return insert count
     */
    int insertSelective(Article article);

    /**
     * update
     *
     * @param article article
     * @return update count
     */
    int updateByIdSelective(Article article);

    /**
     * update pid
     *
     * @param id       id
     * @param parentId parentId
     * @return update count
     */
    @Update("update article set parent_id = #{parentId} where id = #{id}")
    int updatePid(@Param("id") Long id, @Param("parentId") Long parentId);

    /**
     * update sharable
     *
     * @param id       id
     * @param sharable sharable
     * @return update count
     */
    @Update("update article set sharable = #{sharable} where id = #{id}")
    int updateSharable(@Param("id") Long id, @Param("sharable") SharableEnum sharable);

    /**
     * update status
     *
     * @param id     id
     * @param status status
     * @return update count
     */
    @Update("update article set status = #{status} where id = #{id}")
    int updateStatus(@Param("id") Long id, @Param("status") int status);

    /**
     * find
     *
     * @param columnId columnId
     * @param status   status
     * @return Article
     */
    @Select("select id, `title`, `parent_id` from article where column_id = #{columnId} and `status` = #{status}"
            + " order by title,created_date")
    List<ArticleList> findListByColumnIdAndStatus(@Param("columnId") Long columnId, @Param("status") Integer status);

    /**
     * find
     *
     * @param status status
     * @return Article
     */
    @Select("select id, `title`, `parent_id` from article where `status` = #{status}"
            + " order by title,created_date")
    List<ArticleList> findListByStatus(@Param("status") Integer status);

    /**
     * find
     *
     * @param columnId     columnId
     * @param status       status
     * @param sharableList sharableList
     * @return Article
     */
    List<ArticleList> findSharedListByColumnId(@Param("columnId") Long columnId, @Param("status") Integer status,
                                               @Param("sharableList") List<SharableEnum> sharableList);

    /**
     * find all by column id
     *
     * @param columnId column id
     * @param status   status
     * @return all article
     */
    @Select("select * from article where column_id = #{columnId} and `status` = #{status}")
    List<Article> findAllByColumnId(@Param("columnId") Long columnId, @Param("status") Integer status);

    /**
     * find all
     *
     * @return all article
     */
    @Select("select * from article")
    List<Article> findAll();

    /**
     * find last modified data
     *
     * @param limit limit
     * @return all article
     */
    List<ArticleLastModifiedList> findLastModified(@Param("limit") Integer limit);

    /**
     * delete by status
     *
     * @param status status
     * @return count
     */
    @Update("delete from article where `status` = #{status}")
    int deleteByStatus(@Param("status") int status);

    /**
     * delete by id and status
     *
     * @param articleId articleId
     * @param status    status
     * @return delete count
     */
    @Update("delete from article where id = #{articleId} and `status` = #{status}")
    int deleteByIdAndStatus(@Param("articleId") Long articleId, @Param("status") int status);
}
