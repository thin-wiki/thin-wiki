package wiki.thin.web.controller.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.thin.entity.User;
import wiki.thin.mapper.UserMapper;
import wiki.thin.security.remember.RememberMeService;
import wiki.thin.service.PasswordService;
import wiki.thin.web.vo.LoginVO;
import wiki.thin.web.vo.ResponseVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/pub")
@Slf4j
public class LoginApiController {

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
        final Optional<User> userOptional = userMapper.findByAccount(loginVO.getAccount());
        if (userOptional.isEmpty()) {
            log.warn("user [{}] does not exist.", loginVO.getAccount());
            return ResponseVO.error("user does not exist");
        }
        final User user = userOptional.get();
        boolean checkResult = passwordService.checkPassword(loginVO.getPassword(), user.getPassword());
        if (!checkResult) {
            log.warn("[{}] pass error", loginVO.getAccount());
            return ResponseVO.error("password error");
        }
        rememberMeService.login(request, response, user);
        return ResponseVO.success();
    }
}
