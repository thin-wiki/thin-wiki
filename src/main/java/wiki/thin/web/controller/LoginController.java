package wiki.thin.web.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wiki.thin.entity.User;
import wiki.thin.mapper.UserMapper;
import wiki.thin.service.PasswordService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Beldon
 */
@Controller
@RequiredArgsConstructor
public class LoginController extends BaseController {

    private final UserMapper userMapper;
    private final PasswordService passwordService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam("account") String account, @RequestParam("password") String password,
                          HttpServletResponse response, Model model) throws IOException {
        model.addAttribute("account", account);
        final Optional<User> userOptional = userMapper.findByAccount(account);
        if (userOptional.isEmpty()) {
            model.addAttribute("errorMsg", "账号不存在");
            return "login";
        }
        final User user = userOptional.get();
        boolean checkResult = passwordService.checkPassword(password, user.getPassword());
        if (!checkResult) {
            model.addAttribute("errorMsg", "密码错误");
            return "login";
        }
        StpUtil.login(user.getId());
        response.sendRedirect("/index");
        return "login";
    }


    @GetMapping("/auth_login")
    public String authLogin(Model model) {
        model.addAttribute("account", currentAccount());
        return "auth_login";
    }

    @GetMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {
        StpUtil.logout();
        response.sendRedirect("/index");
    }

}
