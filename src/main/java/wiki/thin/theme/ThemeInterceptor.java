package wiki.thin.theme;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Beldon
 */
public class ThemeInterceptor implements MethodInterceptor {

    private static String theme = "theme/default/";

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = invocation.proceed();
        if (result instanceof String) {
            return theme + result;
        }
        if (result instanceof ModelAndView) {
            ModelAndView modelAndView = (ModelAndView) result;
            modelAndView.setViewName(theme + modelAndView.getViewName());
            return modelAndView;
        }
        return result;
    }
}
