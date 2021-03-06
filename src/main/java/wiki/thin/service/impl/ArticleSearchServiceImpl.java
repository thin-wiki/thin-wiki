package wiki.thin.service.impl;

import org.apache.lucene.queryparser.classic.ParseException;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wiki.thin.common.bean.Page;
import wiki.thin.common.util.HtmlUtils;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.entity.Article;
import wiki.thin.mapper.ArticleColumnMapper;
import wiki.thin.mapper.ArticleMapper;
import wiki.thin.search.SearchManager;
import wiki.thin.search.bean.ArticleSearch;
import wiki.thin.service.ArticleSearchService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author beldon
 */
@Service
@Lazy(value = false)
public class ArticleSearchServiceImpl implements ArticleSearchService {

    private final ArticleMapper articleMapper;
    private final ArticleColumnMapper articleColumnMapper;
    private final SearchManager searchManager;

    public ArticleSearchServiceImpl(ArticleMapper articleMapper,
                                    ArticleColumnMapper articleColumnMapper, SearchManager searchManager) {
        this.articleMapper = articleMapper;
        this.articleColumnMapper = articleColumnMapper;
        this.searchManager = searchManager;
    }

    @Override
    public void index(Long articleId) {
        articleMapper.findById(articleId).ifPresent(article -> {
            try {
                indexArticle(article);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void reBuildIndex(Long columnId) {
        final List<Article> articles = articleMapper.findAllByColumnId(columnId);
        for (Article article : articles) {
            try {
                indexArticle(article);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void reBuildIndex() {
        try {
            searchManager.deleteAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final List<Article> all = articleMapper.findAll();
        for (Article article : all) {
            wiki.thin.search.bean.ArticleSearch articleSearch = new wiki.thin.search.bean.ArticleSearch();
            articleSearch.setId(article.getId());
            articleSearch.setTitle(article.getTitle());
            articleSearch.setContent(cleanHtml(article.getContent()));
            articleSearch.setColumnId(article.getColumnId());
            articleSearch.setSelfSharable(article.getSharable());
            articleSearch.setFinalSharable(calcFinalSharable(article));
            articleSearch.setLastModifiedDate(article.getLastModifiedDate());
            try {
                searchManager.index(articleSearch);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Page<ArticleSearch> searchAll(String keyword, int currentPage, int pageSize) throws IOException, ParseException {
        return searchManager.searchAll(keyword, currentPage, pageSize);
    }

    @Override
    public Page<ArticleSearch> searchSharableAll(String keyword, int currentPage, int pageSize) throws IOException, ParseException {
        return searchManager.searchSharableAll(keyword, currentPage, pageSize);
    }

    private void indexArticle(Article article) throws IOException {

        ArticleSearch articleSearch = new ArticleSearch();
        articleSearch.setId(article.getId());
        articleSearch.setTitle(article.getTitle());
        articleSearch.setContent(cleanHtml(article.getContent()));
        articleSearch.setColumnId(article.getColumnId());
        articleSearch.setSelfSharable(article.getSharable());
        articleSearch.setFinalSharable(calcFinalSharable(article));
        articleSearch.setLastModifiedDate(article.getLastModifiedDate());

        searchManager.index(articleSearch);

    }

    private String cleanHtml(String html) {
        if (StringUtils.hasText(html)) {
            return HtmlUtils.replace(Jsoup.parse(html).text());
        }
        return "";
    }

    private SharableEnum calcFinalSharable(Article article) {
        if (SharableEnum.INHERITED.equals(article.getSharable())) {
            final Optional<SharableEnum> sharableEnumOptional = articleColumnMapper.findSharableById(article.getColumnId());
            if (sharableEnumOptional.isPresent()) {
                return sharableEnumOptional.get();
            }
        }
        return article.getSharable();
    }
}
