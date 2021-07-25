package wiki.thin.service.impl;

import org.apache.lucene.queryparser.classic.ParseException;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import wiki.thin.common.bean.Page;
import wiki.thin.common.util.HtmlUtils;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.entity.Post;
import wiki.thin.search.bean.ArticleSearch;
import wiki.thin.service.ArticleSearchService;

import java.io.IOException;

/**
 * @author beldon
 */
@Service
@Lazy(value = false)
public class ArticleSearchServiceImpl implements ArticleSearchService {

//    private final ArticleMapper articleMapper;
//    private final ArticleColumnMapper articleColumnMapper;
//    private final SearchManager searchManager;
//
//    public ArticleSearchServiceImpl(ArticleMapper articleMapper,
//                                    ArticleColumnMapper articleColumnMapper, SearchManager searchManager) {
//        this.articleMapper = articleMapper;
//        this.articleColumnMapper = articleColumnMapper;
//        this.searchManager = searchManager;
//    }

    @Override
    public void index(Long articleId) {
//        articleMapper.findById(articleId).ifPresent(article -> {
//            try {
//                indexArticle(article);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
    }

    @Override
    public void delete(Long articleId) {
//        try {
//            searchManager.deleteById(articleId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void reBuildIndex(Long columnId) {
//        final List<Post> posts = articleMapper.findAllByColumnId(columnId, CommonConstant.STATUS_NORMAL);
//        for (Post post : posts) {
//            try {
//                indexArticle(post);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public void reBuildIndex() {
//        try {
//            searchManager.deleteAll();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        final List<Post> all = articleMapper.findAll();
//        for (Post post : all) {
//            wiki.thin.search.bean.ArticleSearch articleSearch = new wiki.thin.search.bean.ArticleSearch();
//            articleSearch.setId(post.getId());
//            articleSearch.setTitle(post.getTitle());
//            articleSearch.setContent(cleanHtml(post.getContent()));
//            articleSearch.setColumnId(post.getColumnId());
//            articleSearch.setSelfSharable(post.getSharable());
//            articleSearch.setFinalSharable(calcFinalSharable(post));
//            articleSearch.setLastModifiedDate(post.getLastModifiedDate());
//            try {
//                searchManager.index(articleSearch);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public Page<ArticleSearch> searchAll(String keyword, int currentPage, int pageSize) throws IOException, ParseException {
//        return searchManager.searchAll(keyword, currentPage, pageSize);
        return null;
    }

    @Override
    public Page<ArticleSearch> searchSharableAll(String keyword, int currentPage, int pageSize) throws IOException, ParseException {
//        return searchManager.searchSharableAll(keyword, currentPage, pageSize);
        return null;
    }

    private void indexArticle(Post post) throws IOException {

//        ArticleSearch articleSearch = new ArticleSearch();
//        articleSearch.setId(post.getId());
//        articleSearch.setTitle(post.getTitle());
//        articleSearch.setContent(cleanHtml(post.getContent()));
//        articleSearch.setColumnId(post.getColumnId());
//        articleSearch.setSelfSharable(post.getSharable());
//        articleSearch.setFinalSharable(calcFinalSharable(post));
//        articleSearch.setLastModifiedDate(post.getLastModifiedDate());
//
//        searchManager.index(articleSearch);

    }

    private String cleanHtml(String html) {
        if (StringUtils.hasText(html)) {
            return HtmlUtils.replace(Jsoup.parse(html).text());
        }
        return "";
    }

    private SharableEnum calcFinalSharable(Post post) {
//        if (SharableEnum.INHERITED.equals(post.getSharable())) {
//            final Optional<SharableEnum> sharableEnumOptional = articleColumnMapper.findSharableById(post.getColumnId());
//            if (sharableEnumOptional.isPresent()) {
//                return sharableEnumOptional.get();
//            }
//        }
        return post.getSharable();
    }
}
