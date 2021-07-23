package wiki.thin.web.controller.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import wiki.thin.entity.User;
import wiki.thin.repo.UserAutoRepo;
import wiki.thin.security.LoginService;
import wiki.thin.service.PasswordService;
import wiki.thin.web.controller.BaseController;
import wiki.thin.web.vo.LoginVO;
import wiki.thin.web.vo.ResponseVO;
import wiki.thin.web.vo.UserVO;

import javax.validation.Valid;
import java.util.Optional;
import java.util.function.Consumer;
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

//    private final RememberMeService rememberMeService;

    @PostMapping("/login")
    public Mono<ResponseVO> login(@Valid @RequestBody Mono<LoginVO> loginMono, ServerWebExchange exchange) {

        return loginMono.doOnNext(loginVO -> {
            if (ObjectUtils.isEmpty(loginVO.getAccount())) {
                loginVO.setAccount(currentAccount());
            }
        }).flatMap(loginVO -> {

            if (ObjectUtils.isEmpty(loginVO.getAccount())) {
                return Mono.just(ResponseVO.error("请输入用户名"));
            }

            final String account = loginVO.getAccount();
            final Mono<User> userMono = userMapper.findByAccount(account);
            return userMono.map(Optional::of)
                    .defaultIfEmpty(Optional.empty())
                    .map(userOptional -> {
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
                        loginService.login(exchange, user);
                        UserVO userVO = new UserVO();
                        userVO.setId(user.getId());
                        userVO.setAccount(user.getAccount());
                        return ResponseVO.successWithData(userVO);
                    });
        });

    }

//    @NeedLogin
    @PutMapping("/logout")
    public Mono<ResponseVO> logout(ServerWebExchange exchange) {
        loginService.logout(exchange);
        return Mono.just(ResponseVO.success());
    }
}
