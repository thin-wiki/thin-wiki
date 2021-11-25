//package wiki.thin.plugin;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.NoSuchBeanDefinitionException;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.support.DefaultListableBeanFactory;
//import org.springframework.core.ResolvableType;
//
///**
// * @author beldon
// */
//public class PluginBeanFactory extends DefaultListableBeanFactory implements BeanFactory {
//    private final BeanFactory parentBeanFactory;
//
//    public PluginBeanFactory(BeanFactory parentBeanFactory) {
//        super();
//        this.parentBeanFactory = parentBeanFactory;
//    }
//
//    @Override
//    public Object getBean(String s) throws BeansException {
//        try {
//            return super.getBean(s);
//        } catch (BeansException e) {
//            return parentBeanFactory.getBean(s);
//        }
//    }
//
//    @Override
//    public <T> T getBean(String s, Class<T> aClass) throws BeansException {
//        try {
//            return super.getBean(s, aClass);
//        } catch (BeansException e) {
//            return parentBeanFactory.getBean(s, aClass);
//        }
//    }
//
//    @Override
//    public Object getBean(String s, Object... objects) throws BeansException {
//        try {
//            return super.getBean(s, objects);
//        } catch (BeansException e) {
//            return parentBeanFactory.getBean(s, objects);
//        }
//    }
//
//    @Override
//    public <T> T getBean(Class<T> aClass) throws BeansException {
//        try {
//            return super.getBean(aClass);
//        } catch (BeansException e) {
//            return parentBeanFactory.getBean(aClass);
//        }
//    }
//
//    @Override
//    public <T> T getBean(Class<T> aClass, Object... objects) throws BeansException {
//        try {
//            return super.getBean(aClass, objects);
//        } catch (BeansException e) {
//            return parentBeanFactory.getBean(aClass, objects);
//        }
//    }
//
//    @Override
//    public <T> ObjectProvider<T> getBeanProvider(Class<T> aClass) {
//        try {
//            return super.getBeanProvider(aClass);
//        } catch (BeansException e) {
//            return parentBeanFactory.getBeanProvider(aClass);
//        }
//    }
//
//    @Override
//    public <T> ObjectProvider<T> getBeanProvider(ResolvableType resolvableType) {
//        try {
//            return super.getBeanProvider(resolvableType);
//        } catch (BeansException e) {
//            return parentBeanFactory.getBeanProvider(resolvableType);
//        }
//    }
//
//    @Override
//    public boolean containsBean(String s) {
//        return super.containsBean(s) || parentBeanFactory.containsBean(s);
//    }
//
//    @Override
//    public boolean isSingleton(String s) throws NoSuchBeanDefinitionException {
//        try {
//            return super.isSingleton(s);
//        } catch (NoSuchBeanDefinitionException e) {
//            return parentBeanFactory.isSingleton(s);
//        }
//    }
//
//    @Override
//    public boolean isPrototype(String s) throws NoSuchBeanDefinitionException {
//        try {
//            return super.isPrototype(s);
//        } catch (NoSuchBeanDefinitionException e) {
//            return parentBeanFactory.isPrototype(s);
//        }
//    }
//
//    @Override
//    public boolean isTypeMatch(String s, ResolvableType resolvableType) throws NoSuchBeanDefinitionException {
//        try {
//            return super.isTypeMatch(s, resolvableType);
//        } catch (NoSuchBeanDefinitionException e) {
//            return parentBeanFactory.isTypeMatch(s, resolvableType);
//        }
//    }
//
//    @Override
//    public boolean isTypeMatch(String s, Class<?> aClass) throws NoSuchBeanDefinitionException {
//        try {
//            return super.isTypeMatch(s, aClass);
//        } catch (NoSuchBeanDefinitionException e) {
//            return parentBeanFactory.isTypeMatch(s, aClass);
//        }
//    }
//
//    @Override
//    public Class<?> getType(String s) throws NoSuchBeanDefinitionException {
//        try {
//            return super.getType(s);
//        } catch (NoSuchBeanDefinitionException e) {
//            return parentBeanFactory.getType(s);
//        }
//    }
//
//    @Override
//    public Class<?> getType(String s, boolean b) throws NoSuchBeanDefinitionException {
//        try {
//            return super.getType(s, b);
//        } catch (NoSuchBeanDefinitionException e) {
//            return parentBeanFactory.getType(s, b);
//        }
//    }
//
//    @Override
//    public String[] getAliases(String s) {
//        String[] aliases = super.getAliases(s);
//        if (aliases == null) {
//            aliases = parentBeanFactory.getAliases(s);
//        }
//        return aliases;
//    }
//}
