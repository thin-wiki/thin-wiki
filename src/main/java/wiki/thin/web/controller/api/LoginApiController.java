//package wiki.thin.web.controller.api;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.reactivestreams.Publisher;
//import org.reactivestreams.Subscriber;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;
//import wiki.thin.entity.User;
//import wiki.thin.repo.UserAutoRepo;
//import wiki.thin.security.annotation.NeedLogin;
//import wiki.thin.service.PasswordService;
//import wiki.thin.web.controller.BaseController;
//import wiki.thin.web.vo.LoginVO;
//import wiki.thin.web.vo.ResponseVO;
//import wiki.thin.web.vo.UserVO;
//
//import javax.validation.Valid;
//import java.util.Optional;
//
///**
// * @author Beldon
// */
//@RestController
//@RequestMapping("/api/pub")
//@Slf4j
//@AllArgsConstructor
//public class LoginApiController extends BaseController {
//
//    private final UserAutoRepo userMapper;
//
//    private final PasswordService passwordService;
//
////    private final RememberMeService rememberMeService;
//
//    @PostMapping("/login")
//    public Mono<ResponseVO> login(@Valid @RequestBody LoginVO loginVO) {
//
//        String account = loginVO.getAccount();
//        if (!StringUtils.hasText(account)) {
//            account = currentAccount();
//        }
//
//        if (!StringUtils.hasText(account)) {
//            return Mono.from(new Publisher<ResponseVO>() {
//                @Override
//                public void subscribe(Subscriber<? super ResponseVO> subscriber) {
//
//                }
//            });
//            return ResponseVO.error("请输入用户名");
//        }
//
//        final Mono<User> userMono = userMapper.findByAccount(account);
////        if (userOptional.isEmpty()) {
////            log.warn("user [{}] does not exist.", account);
////            return ResponseVO.error("user does not exist");
////        }
////        final User user = userOptional.get();
////        boolean checkResult = passwordService.checkPassword(loginVO.getPassword(), user.getPassword());
////        if (!checkResult) {
////            log.warn("[{}] pass error", account);
////            return ResponseVO.error("password error");
////        }
//////        rememberMeService.login(request, response, user);
////        UserVO userVO = new UserVO();
////        userVO.setId(user.getId());
////        userVO.setAccount(user.getAccount());
////        return ResponseVO.successWithData(userVO);
//        return null;
//    }
//
////    @NeedLogin
////    @PutMapping("/logout")
////    public ResponseVO logout(HttpServletRequest request, HttpServletResponse response) {
////        rememberMeService.logout(request, response);
////        return ResponseVO.success();
////    }
//}
