package wiki.thin.theme;

import org.springframework.stereotype.Component;

/**
 * @author Beldon
 */
@Component
public class ThemeManager {

    public String getThemePath() {
        return "theme/default/";
    }
}
