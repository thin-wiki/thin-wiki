package wiki.thin.web.controller.api;

import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import wiki.thin.entity.User;
import wiki.thin.mapper.UserMapper;
import wiki.thin.security.annotation.NeedLogin;
import wiki.thin.service.PasswordService;
import wiki.thin.web.controller.BaseController;
import wiki.thin.web.vo.LoginVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/pub")
@Slf4j
@RequiredArgsConstructor
public class LoginApiController extends BaseController {

    private final UserMapper userMapper;
    private final PasswordService passwordService;


    @PostMapping("/login")
    public ResponseVO login(@Valid @RequestBody LoginVO loginVO) {

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
        StpUtil.login(user.getId());

        return ResponseVO.successWithData(StpUtil.getTokenValue());
    }

    @NeedLogin
    @PutMapping("/logout")
    public ResponseVO logout() {
        StpUtil.logout();
        return ResponseVO.success();
    }
}
