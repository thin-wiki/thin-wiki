package wiki.thin.web.controller.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import wiki.thin.entity.User;
import wiki.thin.repo.UserAutoRepo;
import wiki.thin.security.LoginService;
import wiki.thin.security.annotation.NeedLogin;
import wiki.thin.service.PasswordService;
import wiki.thin.web.controller.BaseController;
import wiki.thin.web.vo.LoginVO;
import wiki.thin.web.vo.ResponseVO;
import wiki.thin.web.vo.UserVO;

import javax.validation.Valid;
import java.util.function.Function;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/pub")
@Slf4j
@AllArgsConstructor
public class LoginApiController extends BaseController {

    private final UserAutoRepo userMapper;
    private final PasswordService passwordService;
    private final LoginService loginService;

    @PostMapping("/login")
    public Mono<ResponseVO> login(@Valid @RequestBody Mono<LoginVO> loginMono, ServerWebExchange exchange) {

        return loginMono.flatMap(loginVO -> {
            if (ObjectUtils.isEmpty(loginVO.getAccount())) {
                return currentAccount().flatMap(account -> {
                    loginVO.setAccount(account);
                    return Mono.just(loginVO);
                });
            }
            return Mono.just(loginVO);
        }).flatMap(loginVO -> {

            if (ObjectUtils.isEmpty(loginVO.getAccount())) {
                return Mono.just(ResponseVO.error("请输入用户名"));
            }

            final String account = loginVO.getAccount();
            final Mono<User> userMono = userMapper.findByAccount(account);

            return userMono.map(user -> {
                boolean checkResult = passwordService.checkPassword(loginVO.getPassword(), user.getPassword());
                if (!checkResult) {
                    log.warn("[{}] pass error", account);
                    return ResponseVO.error("password error");
                }
                loginService.login(exchange, user);
                UserVO userVO = new UserVO();
                userVO.setId(user.getId());
                userVO.setAccount(user.getAccount());
                return ResponseVO.successWithData(userVO);
            }).defaultIfEmpty(ResponseVO.error("user does not exist"));
        });

    }

    @NeedLogin
    @PutMapping("/logout")
    public Mono<ResponseVO> logout(ServerWebExchange exchange) {
        loginService.logout(exchange);
        return Mono.just(ResponseVO.success());
    }
}
