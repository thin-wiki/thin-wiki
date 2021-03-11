package wiki.thin.install;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author beldon
 */
@Controller
@Profile("install")
public class InstallController {

    private final InstallManager installManager;

    public InstallController(InstallManager installManager) {
        this.installManager = installManager;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model, HttpServletRequest request) throws IOException {
        installManager.checkAndCreateAuthFile();
        final String authFilePath = installManager.getAuthFilePath().toString();
        model.addAttribute("authFilePath", authFilePath);
        model.addAttribute("passCheckToken", installManager.passCheckToken(request));
        return "install/index";
    }
}
