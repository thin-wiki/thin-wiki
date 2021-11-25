//package wiki.thin.plugin;
//
//import org.pf4j.PluginWrapper;
//import org.pf4j.spring.SpringPlugin;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
///**
// * @author beldon
// */
//public abstract class ThinWikiPlugin extends SpringPlugin {
//
//    private final ApplicationContext parentApplicationContext;
//
//    public ThinWikiPlugin(PluginWrapper wrapper, ApplicationContext parentApplicationContext) {
//        super(wrapper);
//        this.parentApplicationContext = parentApplicationContext;
//    }
//
//
//    @Override
//    public void start() {
//        getApplicationContext();
//    }
//
//    @Override
//    protected ApplicationContext createApplicationContext() {
//        final DefaultListableBeanFactory beanFactory = new PluginBeanFactory(parentApplicationContext);
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(beanFactory);
//        applicationContext.setParent(parentApplicationContext);
//        applicationContext.setClassLoader(getWrapper().getPluginClassLoader());
//        applicationContext.register(getConfigurationClass());
//        applicationContext.refresh();
//
//        return applicationContext;
//    }
//
//    /**
//     * 获取 spring configuration 配置类
//     *
//     * @return
//     */
//    protected abstract Class<?>[] getConfigurationClass();
//}
