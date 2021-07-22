//package wiki.thin.theme;
//
//import org.aopalliance.intercept.MethodInterceptor;
//import org.aopalliance.intercept.MethodInvocation;
//import org.springframework.web.servlet.ModelAndView;
//import wiki.thin.constant.CommonConstant;
//import wiki.thin.constant.ConfigConstant;
//import wiki.thin.service.AppConfigService;
//
///**
// * @author Beldon
// */
//public class ThemeInterceptor implements MethodInterceptor {
//
//    private final AppConfigService appConfigService;
//
//    public ThemeInterceptor(AppConfigService appConfigService) {
//        this.appConfigService = appConfigService;
//    }
//
//    @Override
//    public Object invoke(MethodInvocation invocation) throws Throwable {
//        final var currentTheme = currentTheme();
//        if (isSinglePage()) {
//            final Class<?> returnType = invocation.getMethod().getReturnType();
//            if (String.class.equals(returnType)) {
//                return "forward:/theme/" + currentTheme + "/index.html";
//            }
//        }
//        Object result = invocation.proceed();
//        if (result instanceof String) {
//            return currentTheme + "/" + result;
//        }
//        if (result instanceof ModelAndView) {
//            var modelAndView = (ModelAndView) result;
//            modelAndView.setViewName(currentTheme + "/" + modelAndView.getViewName());
//            return modelAndView;
//        }
//        return result;
//    }
//
//    private boolean isSinglePage() {
//        final var themeType = appConfigService.getSystemConfigValue(ConfigConstant.THEME_TYPE,
//                () -> CommonConstant.THEME_TYPE_FREEMARKER);
//        return CommonConstant.THEME_TYPE_SINGLE_PAGE.equals(themeType);
//    }
//
//    private String currentTheme() {
//        return appConfigService.getSystemConfigValue(ConfigConstant.THEME,
//                () -> CommonConstant.THEME_DEFAULT);
//    }
//}
