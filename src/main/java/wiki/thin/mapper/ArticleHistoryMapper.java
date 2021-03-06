package wiki.thin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import wiki.thin.entity.ArticleHistory;

/**
 * @author Beldon
 */
@Mapper
@Repository
public interface ArticleHistoryMapper {

    /**
     * insert
     *
     * @param articleHistory articleHistory
     * @return count
     */
    int insertSelective(ArticleHistory articleHistory);
}
