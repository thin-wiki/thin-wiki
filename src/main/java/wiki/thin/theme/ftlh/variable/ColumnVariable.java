package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.entity.ArticleColumn;
import wiki.thin.entity.mini.ArticleColumnShort;
import wiki.thin.mapper.ArticleColumnMapper;
import wiki.thin.mapper.ArticleMapper;

import java.util.Optional;

/**
 * @author Beldon
 */
@Service
@Lazy(value = false)
public class ColumnVariable extends BaseVariable {

    private final ArticleColumnMapper articleColumnMapper;

    private final ArticleMapper articleMapper;

    protected ColumnVariable(ArticleColumnMapper articleColumnMapper, ArticleMapper articleMapper) {
        super("columnVar");
        this.articleColumnMapper = articleColumnMapper;
        this.articleMapper = articleMapper;
    }

    public String columnPath(Long columnId) {
        final ArticleColumn column = articleColumnMapper.selectById(columnId);
        if (column != null) {
            return column.getPath();
        }
        return "";
    }

    public Optional<String> columnTitle(String columnPath) {
        final Optional<ArticleColumnShort> columnOptional = articleColumnMapper.findShortByPath(columnPath);
        if (columnOptional.isEmpty()) {
            return Optional.empty();
        }

        final ArticleColumnShort articleColumnShort = columnOptional.get();
        if (isLogin() || SharableEnum.SHAREABLE.equals(articleColumnShort.getSharable())) {
            return Optional.of(articleColumnShort.getTitle());
        }

        return Optional.empty();
    }

    public boolean existArticle(Long columnId) {
        return articleMapper.countByColumnId(columnId) > 0;
    }

}
