package wiki.thin.web.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.thin.constant.ConfigConstant;
import wiki.thin.entity.User;
import wiki.thin.mapper.UserMapper;
import wiki.thin.security.OnlineUserManager;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.security.remember.RememberMeService;
import wiki.thin.service.AppConfigService;
import wiki.thin.service.PasswordService;
import wiki.thin.web.controller.BaseController;
import wiki.thin.web.vo.ChangePasswordVO;
import wiki.thin.web.vo.ResponseVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

/**
 * @author beldon
 */
@RequestMapping("/api/admin/user")
@RestController
@NeedAuth
@Slf4j
public class UserAdminController extends BaseController {
    private final UserMapper userMapper;
    private final PasswordService passwordService;
    private final RememberMeService rememberMeService;
    private final AppConfigService appConfigService;
    private final OnlineUserManager onlineUserManager;

    public UserAdminController(UserMapper userMapper, PasswordService passwordService, RememberMeService rememberMeService,
                               AppConfigService appConfigService, OnlineUserManager onlineUserManager) {
        this.userMapper = userMapper;
        this.passwordService = passwordService;
        this.rememberMeService = rememberMeService;
        this.appConfigService = appConfigService;
        this.onlineUserManager = onlineUserManager;
    }

    @PutMapping("/password")
    public ResponseVO changePass(@Valid @RequestBody ChangePasswordVO passwordVO, HttpServletRequest request, HttpServletResponse response) {
        final Optional<User> userOptional = userMapper.findByAccount(currentAccount());
        if (userOptional.isEmpty()) {
            return ResponseVO.error("用户不存在");
        }

        final User user = userOptional.get();
        boolean checkResult = passwordService.checkPassword(passwordVO.getOldPassword(), user.getPassword());
        if (!checkResult) {
            log.warn("[{}] pass error", user.getAccount());
            return ResponseVO.error("旧密码错误");
        }

        final String newPassEncode = passwordService.encode(passwordVO.getNewPassword());
        userMapper.updatePassword(user.getId(), newPassEncode);

        //退出登录
        rememberMeService.logout(request, response);

        //更新记住密码秘钥
        appConfigService.updateSysConfig(ConfigConstant.SYS_REMEMBER_ME_SECRET_KEY, UUID.randomUUID().toString());

        //清除所有 session
        onlineUserManager.clearAll();

        return ResponseVO.success();
    }
}
