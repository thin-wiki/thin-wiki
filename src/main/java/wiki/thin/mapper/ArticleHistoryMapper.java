package wiki.thin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wiki.thin.entity.ArticleHistory;

@Mapper
public interface ArticleHistoryMapper extends BaseMapper<ArticleHistory> {

}