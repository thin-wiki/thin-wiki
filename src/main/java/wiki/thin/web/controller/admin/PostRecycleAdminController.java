package wiki.thin.web.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import wiki.thin.constant.CommonConstant;
import wiki.thin.entity.Post;
import wiki.thin.repo.PostAutoRepo;
import wiki.thin.web.vo.ResponseVO;

import java.util.List;

/**
 * @author Beldon
 */
@RequestMapping("/api/admin/post/recycle")
@RestController
@AllArgsConstructor
public class PostRecycleAdminController {

    private final PostAutoRepo postAutoRepo;

    /**
     * 获取回收站数据
     *
     * @return
     */
    @GetMapping
    public Mono<ResponseVO<List<Post>>> list() {
        final Sort sort = Sort.by(Sort.Direction.DESC, "lastModifiedDate");
        final Flux<Post> postFlux = postAutoRepo.findByStatus(CommonConstant.STATUS_RECYCLE, sort);
        return postFlux.collectList()
                .flatMap(posts -> Mono.just(ResponseVO.successWithData(posts)));
    }

    /**
     * @return response
     */
    @DeleteMapping("/clean")
    public Mono<ResponseVO> clean() {
        return postAutoRepo.deleteByStatus(CommonConstant.STATUS_RECYCLE).thenReturn(ResponseVO.success());
    }

    /**
     * @return response
     */
    @DeleteMapping("/{articleId}")
    public Mono<ResponseVO> delete(@PathVariable("articleId") Long articleId) {
        return postAutoRepo.deleteByIdAndStatus(articleId, CommonConstant.STATUS_RECYCLE)
                .thenReturn(ResponseVO.success());
    }

    /**
     * @return response
     */
    @PutMapping("/{articleId}/restore")
    public Mono<ResponseVO> restore(@PathVariable("articleId") Long articleId) {

        return postAutoRepo.findById(articleId)
                .flatMap(post -> {
                    post.setStatus(CommonConstant.STATUS_NORMAL);
                    if (!CommonConstant.DEFAULT_PARENT_ID.equals(post.getParentId())) {
                        return postAutoRepo.findById(post.getParentId())
                                .then(postAutoRepo.save(post))
                                .switchIfEmpty(postAutoRepo.save(markDefaultParent(post)));
                    }
                    return postAutoRepo.save(post);
                }).thenReturn(ResponseVO.success())
                .defaultIfEmpty(ResponseVO.error("找不到指定记录"));
    }

    public Post markDefaultParent(Post post) {
        post.setParentId(CommonConstant.DEFAULT_PARENT_ID);
        return post;
    }

}
