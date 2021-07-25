package wiki.thin.web.controller.admin;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import wiki.thin.constant.ConfigConstant;
import wiki.thin.entity.User;
import wiki.thin.repo.UserAutoRepo;
import wiki.thin.security.LoginService;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.service.AppConfigService;
import wiki.thin.service.PasswordService;
import wiki.thin.web.controller.BaseController;
import wiki.thin.web.vo.ChangePasswordVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;
import java.util.UUID;
import java.util.function.Function;

/**
 * @author beldon
 */
@RequestMapping("/api/admin/user")
@RestController
@NeedAuth
@Slf4j
@AllArgsConstructor
public class UserAdminController extends BaseController {
    private final UserAutoRepo userAutoRepo;
    private final PasswordService passwordService;
    private final AppConfigService appConfigService;
    private final LoginService loginService;


    @PutMapping("/password")
    public Mono<ResponseVO> changePass(@Valid @RequestBody ChangePasswordVO passwordVO, ServerWebExchange exchange) {

        return currentAccount()
                .flatMap(userAutoRepo::findByAccount)
                .flatMap(user -> {
                    boolean checkResult = passwordService.checkPassword(passwordVO.getOldPassword(), user.getPassword());
                    if (!checkResult) {
                        log.warn("[{}] pass error", user.getAccount());
                        return Mono.just(ResponseVO.error("旧密码错误"));
                    }

                    final String newPassEncode = passwordService.encode(passwordVO.getNewPassword());
                    user.setPassword(newPassEncode);

                    return userAutoRepo.save(user)
                            .then(appConfigService.updateSysConfig(ConfigConstant.SYS_REMEMBER_ME_SECRET_KEY, UUID.randomUUID().toString()))
                            .map(appConfig -> ResponseVO.success())
                            .doFinally(signalType -> loginService.logout(exchange));
                }).defaultIfEmpty(ResponseVO.error("用户不存在"));
    }
}
