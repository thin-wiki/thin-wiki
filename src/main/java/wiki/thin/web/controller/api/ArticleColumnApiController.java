package wiki.thin.web.controller.api;

import org.springframework.web.bind.annotation.*;
import wiki.thin.constant.CommonConstant;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.entity.ArticleColumn;
import wiki.thin.entity.mini.ArticleColumnList;
import wiki.thin.entity.mini.ArticleColumnShort;
import wiki.thin.entity.mini.ArticleList;
import wiki.thin.mapper.ArticleColumnMapper;
import wiki.thin.mapper.ArticleMapper;
import wiki.thin.service.ArticleSearchService;
import wiki.thin.web.vo.ArticleColumnModifyVO;
import wiki.thin.web.vo.ResponseVO;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@RestController
@RequestMapping("/api/article/column")
public class ArticleColumnApiController {

    private final ArticleColumnMapper articleColumnMapper;
    private final ArticleMapper articleMapper;
    private final ArticleSearchService articleSearchService;

    public ArticleColumnApiController(ArticleColumnMapper articleColumnMapper, ArticleMapper articleMapper,
                                      ArticleSearchService articleSearchService) {
        this.articleColumnMapper = articleColumnMapper;
        this.articleMapper = articleMapper;
        this.articleSearchService = articleSearchService;
    }

    @GetMapping("/{columnPath}")
    public ResponseVO getColumn(@PathVariable String columnPath) {
        final Optional<ArticleColumn> columnOptional = articleColumnMapper.findByPath(columnPath);
        if (columnOptional.isEmpty()) {
            return ResponseVO.error("找不到指定记录");
        }

        return ResponseVO.successWithData(columnOptional.get());
    }

    @GetMapping("/{columnPath}/short")
    public ResponseVO getColumnShort(@PathVariable String columnPath) {
        final Optional<ArticleColumnShort> columnOptional = articleColumnMapper.findShortByPath(columnPath);
        if (columnOptional.isEmpty()) {
            return ResponseVO.error("找不到指定记录");
        }

        return ResponseVO.successWithData(columnOptional.get());
    }

    @PostMapping
    public ResponseVO saveColumn(@Valid @RequestBody ArticleColumnModifyVO modifyVO) {
        if (articleColumnMapper.countByPath(modifyVO.getPath()) > 0) {
            return ResponseVO.error("path 已存在");
        }
        ArticleColumn articleColumn = new ArticleColumn();
        articleColumn.setTitle(modifyVO.getTitle());
        articleColumn.setPath(modifyVO.getPath());
        articleColumn.setContent(modifyVO.getContent());
        articleColumn.setSharable(SharableEnum.SHAREABLE);
        articleColumnMapper.insertSelective(articleColumn);
        return ResponseVO.success();
    }

    @PutMapping("/{columnId}")
    public ResponseVO updateColumn(@PathVariable Long columnId, @Valid @RequestBody ArticleColumnModifyVO modifyVO) {
        final Optional<ArticleColumn> columnOptional = articleColumnMapper.findById(columnId);

        if (columnOptional.isEmpty()) {
            return ResponseVO.error("找不到指定记录");
        }

        ArticleColumn articleColumn = columnOptional.get();

        if (!articleColumn.getPath().equals(modifyVO.getPath())
                && articleColumnMapper.countByPath(modifyVO.getPath()) > 0) {
            return ResponseVO.error("path [" + modifyVO.getPath() + "] 已存在");
        }

        articleColumn.setTitle(modifyVO.getTitle());
        articleColumn.setPath(modifyVO.getPath());
        articleColumn.setContent(modifyVO.getContent());
        articleColumnMapper.updateByIdSelective(articleColumn);
        return ResponseVO.success();
    }

    @DeleteMapping("/{columnId}")
    public ResponseVO deleteColumn(@PathVariable Long columnId) {

        if (articleMapper.countByColumnId(columnId) > 0) {
            return ResponseVO.error("类目下存在文章，不能删除");
        }

        articleColumnMapper.deleteById(columnId);
        return ResponseVO.success();
    }

    @PutMapping("/{columnId}/share")
    public ResponseVO updateSharable(@PathVariable Long columnId, @RequestParam("shareable") SharableEnum sharable) {
        articleColumnMapper.updateSharable(columnId, sharable);
        articleSearchService.reBuildIndex(columnId);
        return ResponseVO.success();
    }

    @GetMapping
    public ResponseVO listAll() {
        final List<ArticleColumnList> allList = articleColumnMapper.findAllList();
        return ResponseVO.successWithData(allList);
    }

    @GetMapping("/{columnPath}/menu")
    public ResponseVO menuList(@PathVariable String columnPath) {
        final Optional<ArticleColumn> columnOptional = articleColumnMapper.findByPath(columnPath);

        if (columnOptional.isEmpty()) {
            return ResponseVO.error("找不到指定记录");
        }
        final List<ArticleList> articleLists = articleMapper.findListByColumnIdAndStatus(columnOptional.get().getId(), CommonConstant.STATUS_NORMAL);
        return ResponseVO.successWithData(articleLists);
    }
}
