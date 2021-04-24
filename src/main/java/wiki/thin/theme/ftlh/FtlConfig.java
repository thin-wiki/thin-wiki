package wiki.thin.theme.ftlh;

import freemarker.template.TemplateModelException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import wiki.thin.core.env.EnvManager;
import wiki.thin.theme.ThemeManager;

/**
 * @author Beldon
 */
@Configuration
@Lazy(value = false)
public class FtlConfig {

    public FtlConfig(FreeMarkerConfigurer freeMarkerConfigurer,
                     ThemeManager themeManager) throws TemplateModelException {

        final freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
        configuration.setSharedVariable("theme", themeManager);
        configuration.setSharedVariable("version", EnvManager.BUILD_INFO.getVersion());

    }

}
