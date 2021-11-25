package wiki.thin.theme.ftlh.variable;

import cn.dev33.satoken.stp.StpUtil;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * base variable
 *
 * @author Beldon
 */
@Slf4j
public abstract class BaseVariable {

    private final String variableName;

    @Autowired
    protected FreeMarkerConfigurer freeMarkerConfigurer;

    protected BaseVariable(String variableName) {
        this.variableName = variableName;
    }

    @PostConstruct
    public void init() throws TemplateModelException {
        freeMarkerConfigurer.getConfiguration().setSharedVariable(variableName, this);
        freeMarkerConfigurer.getConfiguration().removeAutoInclude(variableName);
        if (log.isDebugEnabled()) {
            log.info("create [{}] variable", variableName);
        }
    }

    protected boolean isLogin() {
        return StpUtil.isLogin();
    }

    @PreDestroy
    private void destroy() {
        if (log.isDebugEnabled()) {
            log.info("destroy [{}] variable", variableName);
        }
    }
}
