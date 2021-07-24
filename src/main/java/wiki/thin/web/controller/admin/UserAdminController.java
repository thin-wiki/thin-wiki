//package wiki.thin.web.controller.admin;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//import wiki.thin.constant.ConfigConstant;
//import wiki.thin.entity.User;
//import wiki.thin.repo.UserAutoRepo;
//import wiki.thin.security.LoginService;
//import wiki.thin.security.annotation.NeedAuth;
//import wiki.thin.service.AppConfigService;
//import wiki.thin.service.PasswordService;
//import wiki.thin.web.controller.BaseController;
//import wiki.thin.web.vo.ChangePasswordVO;
//import wiki.thin.web.vo.ResponseVO;
//
//import javax.validation.Valid;
//import java.util.Optional;
//import java.util.UUID;
//
///**
// * @author beldon
// */
//@RequestMapping("/api/admin/user")
//@RestController
//@NeedAuth
//@Slf4j
//@AllArgsConstructor
//public class UserAdminController extends BaseController {
//    private final UserAutoRepo userAutoRepo;
//    private final PasswordService passwordService;
//    private final AppConfigService appConfigService;
//    private final LoginService loginService;
//
//
//    @PutMapping("/password")
//    public Mono<ResponseVO> changePass(@Valid @RequestBody ChangePasswordVO passwordVO, ServerWebExchange exchange) {
//        final Mono<User> userMono = userAutoRepo.findByAccount(currentAccount());
//
//        final Optional<User> userOptional = userMapper.findByAccount(currentAccount());
//        if (userOptional.isEmpty()) {
//            return ResponseVO.error("用户不存在");
//        }
//
//        final User user = userOptional.get();
//        boolean checkResult = passwordService.checkPassword(passwordVO.getOldPassword(), user.getPassword());
//        if (!checkResult) {
//            log.warn("[{}] pass error", user.getAccount());
//            return ResponseVO.error("旧密码错误");
//        }
//
//        final String newPassEncode = passwordService.encode(passwordVO.getNewPassword());
//        userMapper.updatePassword(user.getId(), newPassEncode);
//
//        //退出登录
//        loginService.logout(exchange);
//
//        //更新记住密码秘钥
//        appConfigService.updateSysConfig(ConfigConstant.SYS_REMEMBER_ME_SECRET_KEY, UUID.randomUUID().toString());
//
//        //清除所有 session
////        onlineUserManager.clearAll();
//
//        return ResponseVO.success();
//    }
//}
