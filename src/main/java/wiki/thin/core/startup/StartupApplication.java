package wiki.thin.core.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.function.Consumer;

/**
 * @author beldon
 */
public class StartupApplication {

    private StartupApplication() {

    }

    /**
     * 启动应用
     *
     * @param args                  args
     * @param defaultPrimarySources defaultPrimarySources
     * @return SpringApplication
     */
    public static SpringApplication run(String[] args, Class<?>... defaultPrimarySources) {
        return run(args, (app) -> {
        }, defaultPrimarySources);
    }

    public static SpringApplication run(String[] args, Consumer<SpringApplication> beforeRunConsumer, Class<?>... defaultPrimarySources) {
        SpringApplication app = null;
        ServiceLoader<StartupApplicationProvider> providers = ServiceLoader.load(StartupApplicationProvider.class);
        for (StartupApplicationProvider provider : providers) {
            final Optional<Class<?>> appClassOptional = provider.getStartupApplication();
            if (appClassOptional.isPresent()) {
                app = new SpringApplication(appClassOptional.get());
                provider.beforeRun(app);
                break;
            }
        }
        if (app == null) {
            app = new SpringApplication(defaultPrimarySources);
        }
        app.addListeners(new ApplicationPidFileWriter("app.pid"));

        beforeRunConsumer.accept(app);
        app.run(args);

        return app;
    }
}
