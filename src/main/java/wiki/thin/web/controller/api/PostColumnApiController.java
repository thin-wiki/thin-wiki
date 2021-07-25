package wiki.thin.web.controller.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.entity.PostColumn;
import wiki.thin.repo.PostAutoRepo;
import wiki.thin.repo.PostColumnAutoRepo;
import wiki.thin.security.annotation.NeedLogin;
import wiki.thin.service.ArticleSearchService;
import wiki.thin.web.vo.PostColumnModifyVO;
import wiki.thin.web.vo.PostColumnListVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/post/column")
@AllArgsConstructor
public class PostColumnApiController {

    private final PostColumnAutoRepo postColumnAutoRepo;
    private final PostAutoRepo postAutoRepo;
    private final ArticleSearchService articleSearchService;


    @PostMapping
    @NeedLogin
    public Mono<ResponseVO> saveColumn(@Valid @RequestBody PostColumnModifyVO modifyVO) {

        return postColumnAutoRepo.countByPath(modifyVO.getPath())
                .flatMap(count -> {
                    if (count > 0) {
                        return Mono.just(ResponseVO.error("path 已存在"));
                    }
                    PostColumn postColumn = new PostColumn();
                    postColumn.setTitle(modifyVO.getTitle());
                    postColumn.setPath(modifyVO.getPath());
                    postColumn.setContent(modifyVO.getContent());
                    postColumn.setSharable(SharableEnum.SHAREABLE);

                    return postColumnAutoRepo.save(postColumn)
                            .thenReturn(ResponseVO.success());
                });
    }

    @NeedLogin
    @PutMapping("/{columnId}")
    public Mono<ResponseVO> updateColumn(@PathVariable Long columnId, @Valid @RequestBody PostColumnModifyVO modifyVO) {

        return postColumnAutoRepo.findById(columnId)
                .flatMap(column -> {

                    column.setTitle(modifyVO.getTitle());
                    column.setContent(modifyVO.getContent());

                    if (!column.getPath().equals(modifyVO.getPath())) {
                        return postColumnAutoRepo.countByPath(modifyVO.getPath())
                                .flatMap(count -> {
                                    if (count > 0) {
                                        return Mono.just(ResponseVO.error("path [" + modifyVO.getPath() + "] 已存在"));
                                    }
                                    column.setPath(modifyVO.getPath());
                                    return postColumnAutoRepo.save(column)
                                            .thenReturn(ResponseVO.success());
                                });
                    } else {
                        return postColumnAutoRepo.save(column)
                                .thenReturn(ResponseVO.success());
                    }
                }).defaultIfEmpty(ResponseVO.error("找不到指定记录"));

    }

    @NeedLogin
    @DeleteMapping("/{columnId}")
    public Mono<ResponseVO> deleteColumn(@PathVariable Long columnId) {
        return postAutoRepo.countByColumnId(columnId).flatMap(count -> {
            if (count > 0) {
                return Mono.just(ResponseVO.error("类目下存在文章，不能删除"));
            }
            return postColumnAutoRepo.deleteById(columnId).thenReturn(ResponseVO.success());
        });
    }

    @NeedLogin
    @PutMapping("/{columnId}/share")
    public Mono<ResponseVO> updateSharable(@PathVariable Long columnId, @RequestParam("shareable") SharableEnum sharable) {
        return postColumnAutoRepo.findById(columnId)
                .flatMap(column -> {
                    column.setSharable(sharable);
                    return postColumnAutoRepo.save(column)
                            .doOnSuccess(c -> articleSearchService.reBuildIndex(c.getId()))
                            .thenReturn(ResponseVO.success());
                }).defaultIfEmpty(ResponseVO.error("找不到指定类目"));
    }

    @GetMapping
    public Mono<ResponseVO<List<PostColumnListVO>>> list() {
        return postColumnAutoRepo.findAll()
                .map(column -> {
                    PostColumnListVO list = new PostColumnListVO();
                    BeanUtils.copyProperties(column, list);
                    return list;
                }).collectList()
                .map(ResponseVO::successWithData);
    }
}
