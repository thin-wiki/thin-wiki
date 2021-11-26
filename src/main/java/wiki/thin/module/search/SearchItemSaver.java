package wiki.thin.module.search;

import wiki.thin.module.search.bean.ArticleSearch;

import java.io.IOException;

/**
 * @author beldon
 */
public interface SearchItemSaver {
    /**
     * save
     *
     * @param articleSearch articleSearch
     * @throws IOException IOException
     */
    void save(ArticleSearch articleSearch) throws IOException;
}
