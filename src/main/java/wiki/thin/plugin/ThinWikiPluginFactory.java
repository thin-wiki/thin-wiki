//package wiki.thin.plugin;
//
//import lombok.extern.slf4j.Slf4j;
//import org.pf4j.Plugin;
//import org.pf4j.PluginFactory;
//import org.pf4j.PluginWrapper;
//import org.springframework.context.ApplicationContext;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Modifier;
//
///**
// * @author beldon
// */
//@Slf4j
//public class ThinWikiPluginFactory implements PluginFactory {
//
//    private final ApplicationContext applicationContext;
//
//    public ThinWikiPluginFactory(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    @Override
//    public Plugin create(PluginWrapper pluginWrapper) {
//        String pluginClassName = pluginWrapper.getDescriptor().getPluginClass();
//        log.debug("Create instance for plugin '{}'", pluginClassName);
//
//        Class<?> pluginClass;
//        try {
//            pluginClass = pluginWrapper.getPluginClassLoader().loadClass(pluginClassName);
//        } catch (ClassNotFoundException e) {
//            log.error(e.getMessage(), e);
//            return null;
//        }
//
//        // once we have the class, we can do some checks on it to ensure
//        // that it is a valid implementation of a plugin.
//        int modifiers = pluginClass.getModifiers();
//        if (Modifier.isAbstract(modifiers) || Modifier.isInterface(modifiers)
//                || (!Plugin.class.isAssignableFrom(pluginClass))) {
//            log.error("The plugin class '{}' is not valid", pluginClassName);
//            return null;
//        }
//
//        // create the plugin instance
//        try {
//            Constructor<?> constructor = pluginClass.getConstructor(PluginWrapper.class, ApplicationContext.class);
//            return (Plugin) constructor.newInstance(pluginWrapper, applicationContext);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//
//        return null;
//    }
//}
