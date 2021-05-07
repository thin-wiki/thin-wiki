package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import wiki.thin.constant.CommonConstant;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.entity.Article;
import wiki.thin.entity.mini.ArticleLastModifiedList;
import wiki.thin.entity.mini.ArticleList;
import wiki.thin.mapper.ArticleColumnMapper;
import wiki.thin.mapper.ArticleMapper;

import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@Service
@Lazy(value = false)
public class ArticleVariable extends BaseVariable {

    private final ArticleMapper articleMapper;
    private final ArticleColumnMapper articleColumnMapper;

    protected ArticleVariable(ArticleMapper articleMapper, ArticleColumnMapper articleColumnMapper) {
        super("articleVar");
        this.articleMapper = articleMapper;
        this.articleColumnMapper = articleColumnMapper;
    }

    public Optional<Article> article(Long articleId) {
        final var articleOptional = articleMapper.findById(articleId);
        if (articleOptional.isEmpty()) {
            return Optional.empty();
        }
        if (canView(articleOptional.get())) {
            return articleOptional;
        }
        return Optional.empty();
    }

    public List<ArticleLastModifiedList> lastModified() {
        return articleMapper.findLastModified(15);
    }

    public List<ArticleList> recycleArticles() {
        return articleMapper.findListByStatus(CommonConstant.STATUS_RECYCLE);
    }

    private boolean canView(Article article) {
        final var sharable = article.getSharable();
        if (isLogin() || SharableEnum.SHAREABLE.equals(article.getSharable())) {
            return true;
        }

        if (SharableEnum.PRIVATE.equals(sharable)) {
            return false;
        }

        final var columnSharableOptional = articleColumnMapper.findSharableById(article.getColumnId());
        if (columnSharableOptional.isEmpty()) {
            return false;
        }

        return SharableEnum.SHAREABLE.equals(columnSharableOptional.get());

    }
}
