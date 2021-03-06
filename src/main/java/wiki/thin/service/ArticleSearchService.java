package wiki.thin.service;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.scheduling.annotation.Async;
import wiki.thin.common.bean.Page;
import wiki.thin.search.bean.ArticleSearch;

import java.io.IOException;

/**
 * article search
 *
 * @author beldon
 */
public interface ArticleSearchService {

    /**
     * index article
     *
     * @param articleId article id
     */
    @Async
    void index(Long articleId);

    /**
     * 重建栏目索引
     *
     * @param columnId columnId
     */
    @Async
    void reBuildIndex(Long columnId);

    /**
     * reindex
     */
    @Async
    void reBuildIndex();

    /**
     * 查询
     *
     * @param keyword     keyword
     * @param currentPage currentPage
     * @param pageSize    pageSize
     * @return page
     * @throws IOException    IOException
     * @throws ParseException ParseException
     */
    Page<ArticleSearch> searchAll(String keyword, int currentPage, int pageSize) throws IOException, ParseException;

    /**
     * 查询
     *
     * @param keyword     keyword
     * @param currentPage currentPage
     * @param pageSize    pageSize
     * @return page
     * @throws IOException    IOException
     * @throws ParseException ParseException
     */
    Page<ArticleSearch> searchSharableAll(String keyword, int currentPage, int pageSize) throws IOException, ParseException;
}
