package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import wiki.thin.common.util.JsonUtils;
import wiki.thin.constant.enums.SharableEnum;
import wiki.thin.entity.mini.ArticleColumnShort;
import wiki.thin.entity.mini.ArticleList;
import wiki.thin.mapper.ArticleColumnMapper;
import wiki.thin.mapper.ArticleMapper;
import wiki.thin.theme.ftlh.variable.entity.NavBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Beldon
 */
@Service
@Lazy(value = false)
public class NavVariable extends BaseVariable {

    private final ArticleMapper articleMapper;

    private final ArticleColumnMapper articleColumnMapper;

    protected NavVariable(ArticleMapper articleMapper, ArticleColumnMapper articleColumnMapper) {
        super("navVar");
        this.articleMapper = articleMapper;
        this.articleColumnMapper = articleColumnMapper;
    }

    /**
     * 如果 column 公开，取 {@link wiki.thin.constant.enums.SharableEnum#SHAREABLE} 、{@link wiki.thin.constant.enums.SharableEnum#INHERITED} 的文章
     *
     * <p>如果 column 私有，取 {@link wiki.thin.constant.enums.SharableEnum#SHAREABLE} 的文章
     *
     * @param columnPath columnPath
     * @return 菜单列表
     */
    public String getMenus(String columnPath) {
        List<NavBean> navBeans = new ArrayList<>();
        final Optional<ArticleColumnShort> columnOptional = articleColumnMapper.findShortByPath(columnPath);
        if (columnOptional.isPresent()) {
            final ArticleColumnShort column = columnOptional.get();
            final List<ArticleList> articles;
            if (isLogin()) {
                articles = articleMapper.findListByColumnId(column.getId());
            } else {
                final List<SharableEnum> sharableEnums;
                if (SharableEnum.SHAREABLE.equals(column.getSharable())) {
                    sharableEnums = Arrays.asList(SharableEnum.SHAREABLE, SharableEnum.INHERITED);
                } else {
                    sharableEnums = Arrays.asList(SharableEnum.SHAREABLE);
                }
                articles = articleMapper.findSharedListByColumnId(column.getId(), sharableEnums);
            }
            for (ArticleList article : articles) {
                NavBean navBean = new NavBean();
                navBean.setId(article.getId());
                navBean.setName(article.getTitle());
                navBean.setPid(article.getParentId());
                navBeans.add(navBean);
            }
        }
        return JsonUtils.toJsonString(navBeans);
    }

}
