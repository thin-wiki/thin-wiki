package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.entity.ArticleColumn;
import wiki.thin.entity.mini.ArticleColumnList;
import wiki.thin.mapper.ArticleColumnMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@Service
@Lazy(value = false)
public class ArticleColumnVariable extends BaseVariable {

    private final ArticleColumnMapper articleColumnMapper;

    public ArticleColumnVariable(ArticleColumnMapper articleColumnMapper) {
        super("articleColumnVar");
        this.articleColumnMapper = articleColumnMapper;
    }

    public List<ArticleColumn> getAll() {
        return articleColumnMapper.findAll();
    }

    public List<ArticleColumnList> getAllList() {
        if (isLogin()) {
            return articleColumnMapper.findAllList();
        }
        return articleColumnMapper.findSharedList(SharableEnum.SHAREABLE);
    }

    public Optional<ArticleColumn> getByPath(String path) {
        final Optional<ArticleColumn> columnOptional = articleColumnMapper.findByPath(path);
        if (columnOptional.isEmpty()) {
            return Optional.empty();
        }
        if (isLogin()) {
            return columnOptional;
        }
        final ArticleColumn column = columnOptional.get();
        if (SharableEnum.SHAREABLE.equals(column.getSharable())) {
            return columnOptional;
        }
        return Optional.empty();
    }
}
