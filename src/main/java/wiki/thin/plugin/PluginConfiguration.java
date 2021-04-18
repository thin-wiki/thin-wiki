package wiki.thin.plugin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author beldon
 */
@Configuration
public class PluginConfiguration {
    @Bean
    public ThinWikiPluginManager pluginManager() {
        return new ThinWikiPluginManager();
    }
}
