package wiki.thin.plugin;

import org.pf4j.PluginFactory;
import org.pf4j.spring.SpringPluginManager;


/**
 * @author beldon
 */
public class ThinWikiPluginManager extends SpringPluginManager {

    @Override
    protected PluginFactory getPluginFactory() {
        return new ThinWikiPluginFactory(getApplicationContext());
    }
}
