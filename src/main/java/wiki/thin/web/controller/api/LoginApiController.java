package wiki.thin.web.controller.api;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import wiki.thin.entity.User;
import wiki.thin.repo.UserAutoRepo;
import wiki.thin.service.PasswordService;
import wiki.thin.web.controller.BaseController;
import wiki.thin.web.vo.LoginVO;
import wiki.thin.web.vo.ResponseVO;
import wiki.thin.web.vo.UserVO;

import javax.validation.Valid;
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

//    private final RememberMeService rememberMeService;
    
    @PostMapping("/login")
    public Mono<ResponseVO> login(@Valid @RequestBody Mono<LoginVO> loginMono) {
        
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
            
            return userMono.map(user -> {
                if (user == null) {
                    log.warn("user [{}] does not exist.", account);
                    return ResponseVO.error("user does not exist");
                }
                boolean checkResult = passwordService.checkPassword(loginVO.getPassword(), user.getPassword());
                if (!checkResult) {
                    log.warn("[{}] pass error", account);
                    return ResponseVO.error("password error");
                }
                UserVO userVO = new UserVO();
                userVO.setId(user.getId());
                userVO.setAccount(user.getAccount());
                return ResponseVO.successWithData(userVO);
            });
        });
    }

//    @NeedLogin
//    @PutMapping("/logout")
//    public ResponseVO logout(HttpServletRequest request, HttpServletResponse response) {
//        rememberMeService.logout(request, response);
//        return ResponseVO.success();
//    }
}
