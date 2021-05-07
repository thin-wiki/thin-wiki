package wiki.thin.search;

import org.apache.lucene.queryparser.classic.ParseException;
import wiki.thin.common.bean.Page;
import wiki.thin.search.bean.ArticleSearch;

import java.io.IOException;
import java.util.List;

/**
 * @author Beldon
 */
public interface SearchManager {

    /**
     * index
     *
     * @param articleSearch articleSearch
     * @throws IOException IOException
     */
    default void index(ArticleSearch articleSearch) throws IOException {
        index(searchItemSaver -> searchItemSaver.save(articleSearch));
    }

    /**
     * index
     *
     * @param articleSearches articleSearches
     * @throws IOException IOException
     */
    default void index(List<ArticleSearch> articleSearches) throws IOException {
        index(saver -> {
            for (ArticleSearch articleSearch : articleSearches) {
                saver.save(articleSearch);
            }
        });
    }

    /**
     * index
     *
     * @param provider provider
     * @throws IOException IOException
     */
    void index(SearchItemProvider provider) throws IOException;

    /**
     * delete by id
     *
     * @param articleId articleId
     * @throws IOException IOException
     */
    void deleteById(Long articleId) throws IOException;

    /**
     * delete all
     *
     * @throws IOException IOException
     */
    void deleteAll() throws IOException;

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
