package wiki.thin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import wiki.thin.entity.ArticleViewHistory;

import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@Mapper
@Repository
public interface ArticleViewHistoryMapper {

    /**
     * find
     *
     * @param userId userId
     * @return ArticleViewHistory
     */
    @Select("select * from article_view_history where created_by = #{userId} order by last_modified_date")
    List<ArticleViewHistory> findByUserId(@Param("userId") Long userId);

    /**
     * find
     *
     * @param createdBy createdBy
     * @param articleId articleId
     * @return ArticleViewHistory
     */
    @Select("select * from article_view_history where created_by = #{createdBy} and article_id = #{articleId}")
    Optional<ArticleViewHistory> findByCreatedByAndArticleId(@Param("createdBy") Long createdBy, @Param("articleId") Long articleId);

    /**
     * insert
     *
     * @param history history
     * @return insert count
     */
    int insertSelective(ArticleViewHistory history);

    /**
     * clear
     *
     * @param userId userId
     * @param start  start
     * @param end    end
     * @return clear count
     */
    int clearMore(@Param("userId") Long userId, @Param("start") int start, @Param("end") int end);

}
