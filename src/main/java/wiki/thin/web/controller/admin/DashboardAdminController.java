package wiki.thin.web.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import wiki.thin.repo.PostAutoRepo;
import wiki.thin.repo.PostColumnAutoRepo;
import wiki.thin.security.annotation.NeedAuth;
import wiki.thin.web.vo.DashboardTotalVO;
import wiki.thin.web.vo.ResponseVO;

/**
 * @author beldon
 */
@RequestMapping("/api/admin/dashboard")
@RestController
@NeedAuth
@AllArgsConstructor
public class DashboardAdminController {

    private final PostColumnAutoRepo postColumnAutoRepo;
    private final PostAutoRepo postAutoRepo;

    @GetMapping("/total")
    public Mono<ResponseVO<DashboardTotalVO>> total() {
        DashboardTotalVO totalVO = new DashboardTotalVO();

        return postColumnAutoRepo.count()
                .doOnNext(totalVO::setColumnCount)
                .then(postAutoRepo.count())
                .doOnNext(totalVO::setPostCount)
                .thenReturn(ResponseVO.successWithData(totalVO));

    }
}
