package wiki.thin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import wiki.thin.backup.BackupService;
import wiki.thin.security.annotation.NeedAuth;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author beldon
 */
@Controller
@RequestMapping("/backup")
public class BackupController extends BaseController {

    private final BackupService backupService;

    public BackupController(BackupService backupService) {
        this.backupService = backupService;
    }

    @GetMapping
    @NeedAuth
    public String index(Model model) {
        model.addAttribute("backupFiles", backupService.list());
        return "backup/index";
    }

    @NeedAuth
    @GetMapping("/{fileName}")
    public void download(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        backupService.download(fileName, response.getOutputStream());
    }
}
