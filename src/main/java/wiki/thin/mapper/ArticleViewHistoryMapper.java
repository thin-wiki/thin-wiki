package wiki.thin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import wiki.thin.entity.ArticleViewHistory;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleViewHistoryMapper extends BaseMapper<ArticleViewHistory> {
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
}