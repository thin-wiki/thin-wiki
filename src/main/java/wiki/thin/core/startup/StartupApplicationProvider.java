package wiki.thin.core.startup;

import org.springframework.boot.SpringApplication;

import java.util.Optional;

/**
 * @author beldon
 */
public interface StartupApplicationProvider {
    /**
     * 获取启动类
     *
     * @return 获取启动类
     */
    Optional<Class<?>> getStartupApplication();


    /**
     * 启动 app 前
     *
     * @param app app
     */
    default void beforeRun(SpringApplication app) {
    }
}
