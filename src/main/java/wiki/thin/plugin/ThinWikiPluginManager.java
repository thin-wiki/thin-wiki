package wiki.thin.plugin;

import org.pf4j.PluginFactory;
import org.pf4j.RuntimeMode;
import org.pf4j.spring.SpringPluginManager;

import javax.annotation.PostConstruct;
import java.nio.file.Path;


/**
 * @author beldon
 */
public class ThinWikiPluginManager extends SpringPluginManager {

    @Override
    protected PluginFactory getPluginFactory() {
        return new ThinWikiPluginFactory(getApplicationContext());
    }

    @PostConstruct
    public void devLoad() {
        if (RuntimeMode.DEVELOPMENT.equals(getRuntimeMode())) {
            final String pluginId = loadPlugin(Path.of("."));
            startPlugin(pluginId);
        }
    }
}
