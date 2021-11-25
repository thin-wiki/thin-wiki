package wiki.thin.web.controller.admin;

import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiki.thin.entity.User;
import wiki.thin.mapper.UserMapper;
import wiki.thin.service.PasswordService;
import wiki.thin.web.controller.BaseController;
import wiki.thin.web.vo.ChangePasswordVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author beldon
 */
@RequestMapping("/api/admin/user")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserAdminController extends BaseController {
    private final UserMapper userMapper;
    private final PasswordService passwordService;

    @PutMapping("/password")
    public ResponseVO changePass(@Valid @RequestBody ChangePasswordVO passwordVO) {
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
        StpUtil.logout();

        return ResponseVO.success();
    }
}
