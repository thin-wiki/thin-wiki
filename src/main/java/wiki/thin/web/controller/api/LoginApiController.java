package wiki.thin.web.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import wiki.thin.entity.User;
import wiki.thin.mapper.UserMapper;
import wiki.thin.security.annotation.NeedLogin;
import wiki.thin.security.remember.RememberMeService;
import wiki.thin.service.PasswordService;
import wiki.thin.web.controller.BaseController;
import wiki.thin.web.vo.LoginVO;
import wiki.thin.web.vo.ResponseVO;
import wiki.thin.web.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/pub")
@Slf4j
public class LoginApiController extends BaseController {

    private final UserMapper userMapper;

    private final PasswordService passwordService;

    private final RememberMeService rememberMeService;

    public LoginApiController(UserMapper userMapper, PasswordService passwordService,
                              RememberMeService rememberMeService) {
        this.userMapper = userMapper;
        this.passwordService = passwordService;
        this.rememberMeService = rememberMeService;
    }

    @PostMapping("/login")
    public ResponseVO login(@Valid @RequestBody LoginVO loginVO, HttpServletRequest request,
                            HttpServletResponse response) {

        String account = loginVO.getAccount();
        if (!StringUtils.hasText(account)) {
            account = currentAccount();
        }

        if (!StringUtils.hasText(account)) {
            return ResponseVO.error("请输入用户名");
        }

        final Optional<User> userOptional = userMapper.findByAccount(account);
        if (userOptional.isEmpty()) {
            log.warn("user [{}] does not exist.", account);
            return ResponseVO.error("user does not exist");
        }
        final User user = userOptional.get();
        boolean checkResult = passwordService.checkPassword(loginVO.getPassword(), user.getPassword());
        if (!checkResult) {
            log.warn("[{}] pass error", account);
            return ResponseVO.error("password error");
        }
        rememberMeService.login(request, response, user);
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setAccount(user.getAccount());
        return ResponseVO.successWithData(userVO);
    }

    @NeedLogin
    @GetMapping("/logout")
    public ResponseVO logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        rememberMeService.logout(request, response);
        return ResponseVO.success();
    }
}
