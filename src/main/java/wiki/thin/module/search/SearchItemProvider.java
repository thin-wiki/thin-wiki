package wiki.thin.module.search;

import java.io.IOException;

/**
 * @author beldon
 */
public interface SearchItemProvider {

    /**
     * save
     *
     * @param searchItemSaver searchItemSaver
     * @throws IOException IOException
     */
    void save(SearchItemSaver searchItemSaver) throws IOException;
}
