package wiki.thin.web.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import wiki.thin.constant.CommonConstant;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.entity.Post;
import wiki.thin.repo.PostAutoRepo;
import wiki.thin.repo.PostColumnAutoRepo;
import wiki.thin.service.ArticleHistoryService;
import wiki.thin.service.ArticleSearchService;
import wiki.thin.web.vo.ArticleVO;
import wiki.thin.web.vo.PostModifyVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;

/**
 * @author Beldon
 */
@RequestMapping("/api/post")
@RestController
@AllArgsConstructor
public class PostApiController {

    private final PostAutoRepo postAutoRepo;
    private final PostColumnAutoRepo postColumnAutoRepo;
    private final ArticleSearchService articleSearchService;
    private final ArticleHistoryService articleHistoryService;


    /**
     * 添加
     *
     * @param articleVO article
     * @return response
     */
    @PostMapping
    public Mono<ResponseVO> saveArticle(@RequestBody @Valid ArticleVO articleVO) {

        final var columnPath = articleVO.getColumnPath();
        final var parentId = articleVO.getParentId();

        if (columnPath == null && parentId == null) {
            return Mono.just(ResponseVO.error("column path 和 parentId 不能同时为空"));
        }

        var post = new Post();
        post.setTitle(articleVO.getTitle());
        post.setContent(articleVO.getContent());
        post.setSharable(SharableEnum.INHERITED);

        final Mono<Post> postMono;
        if (parentId != null) {
            postMono = postAutoRepo.findById(parentId)
                    .doOnNext(parent -> {
                        post.setParentId(parent.getParentId());
                        post.setColumnId(parent.getColumnId());
                    }).then(Mono.just(post));
        } else {
            postMono = postColumnAutoRepo.findByPath(columnPath)
                    .doOnNext(postColumn -> {
                        post.setParentId(0L);
                        post.setColumnId(postColumn.getId());
                    }).then(Mono.just(post));
        }

        return postMono.flatMap(p -> {
            if (p.getColumnId() == null) {
                return Mono.just(ResponseVO.error("找不到指定 column"));
            }
            return postAutoRepo.save(p)
                    .doOnSuccess(p1 -> {
                        articleSearchService.index(p1.getId());
                        articleHistoryService.saveArticleHistory(p.getId());
                    })
                    .thenReturn(ResponseVO.success());
        });

    }

    @PutMapping("/{articleId}")
    public Mono<ResponseVO> updatePost(@PathVariable Long articleId, @RequestBody @Valid PostModifyVO postVO) {
        return postAutoRepo.findById(articleId)
                .flatMap(post -> {
                    post.setTitle(postVO.getTitle());
                    post.setContent(postVO.getContent());
                    return postAutoRepo.save(post);
                }).doOnNext(post -> {
                    articleSearchService.index(post.getId());
                    articleHistoryService.saveArticleHistory(post.getId());
                })
                .thenReturn(ResponseVO.success())
                .defaultIfEmpty(ResponseVO.error("找不到指定记录"));

    }

    @PutMapping("/{postId}/pid")
    public Mono<ResponseVO> updateParentId(@PathVariable Long postId, @RequestParam("parentId") Long parentId) {
        return postAutoRepo.findById(postId)
                .flatMap(post -> {
                    post.setParentId(parentId);
                    return postAutoRepo.save(post).thenReturn(ResponseVO.success());
                }).defaultIfEmpty(ResponseVO.error("找不到指定记录"));
    }

    @PutMapping("/{postId}/share")
    public Mono<ResponseVO> updateSharable(@PathVariable Long postId, @RequestParam("shareable") SharableEnum sharable) {

        return postAutoRepo.findById(postId)
                .flatMap(post -> {
                    post.setSharable(sharable);
                    return postAutoRepo.save(post).thenReturn(ResponseVO.success());
                }).doOnNext(res -> articleSearchService.index(postId))
                .defaultIfEmpty(ResponseVO.error("找不到指定记录"));

    }

    @DeleteMapping("/{postId}")
    public Mono<ResponseVO> recycle(@PathVariable Long postId) {
        return postAutoRepo.findById(postId)
                .flatMap(post -> {
                    post.setStatus(CommonConstant.STATUS_RECYCLE);
                    return postAutoRepo.save(post).thenReturn(ResponseVO.success());
                }).doOnNext(res -> articleSearchService.delete(postId))
                .defaultIfEmpty(ResponseVO.error("找不到指定记录"));
    }
}
