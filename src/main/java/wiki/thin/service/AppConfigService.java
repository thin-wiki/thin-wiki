package wiki.thin.service;

import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;
import wiki.thin.constant.ConfigConstant;
import wiki.thin.entity.AppConfig;

import java.util.function.Supplier;

/**
 * @author beldon
 */
public interface AppConfigService {

    /**
     * update config
     *
     * @param type        type
     * @param key         key
     * @param value       value
     * @param description description
     */
    Mono<AppConfig> updateConfig(String type, String key, String value, String description);

    /**
     * update config
     *
     * @param type  type
     * @param key   key
     * @param value value
     */
    default Mono<AppConfig> updateConfig(String type, String key, String value) {
        return updateConfig(type, key, value, null);
    }

    /**
     * update system config
     *
     * @param key         key
     * @param value       value
     * @param description description
     */
    default Mono<AppConfig> updateSysConfig(String key, String value, String description) {
        return updateConfig(ConfigConstant.CONFIG_TYPE_SYS, key, value, description);
    }

    /**
     * update system config
     *
     * @param key   key
     * @param value value
     */
    default Mono<AppConfig> updateSysConfig(String key, String value) {
        return updateSysConfig(key, value, null);
    }


    /**
     * get config
     *
     * @param type                 type
     * @param key                  key
     * @param defaultValueSupplier default value
     * @return app config
     */
    Mono<AppConfig> getConfig(String type, String key, Supplier<String> defaultValueSupplier);

    /**
     * get config
     *
     * @param type type
     * @param key  key
     * @return app config
     */
    default Mono<AppConfig> getConfig(String type, String key) {
        return getConfig(type, key, () -> null);
    }

    /**
     * get system config
     *
     * @param key key
     * @return app config
     */
    default Mono<AppConfig> getSystemConfig(String key) {
        return getConfig(ConfigConstant.CONFIG_TYPE_SYS, key);
    }

    /**
     * get config value
     *
     * @param type                 type
     * @param key                  key
     * @param defaultValueSupplier default value supplier
     * @return config value
     */
    default Mono<String> getConfigValue(String type, String key, Supplier<String> defaultValueSupplier) {
        final Mono<AppConfig> configMono = getConfig(type, key, defaultValueSupplier);
        return configMono
                .flatMap(v -> {
                    if (StringUtils.hasText(v.getValue())) {
                        return Mono.just(v.getValue());
                    }
                    final String defaultValue = defaultValueSupplier.get();
                    return updateConfig(type, key, defaultValue).map(AppConfig::getValue);
                });
    }

    /**
     * get config
     *
     * @param type type
     * @param key  key
     * @return config value
     */
    default Mono<String> getConfigValue(String type, String key) {
        return getConfigValue(type, key, () -> "");
    }

    /**
     * get system config
     *
     * @param key                  key
     * @param defaultValueSupplier default value supplier
     * @return config value
     */
    default Mono<String> getSystemConfigValue(String key, Supplier<String> defaultValueSupplier) {
        return getConfigValue(ConfigConstant.CONFIG_TYPE_SYS, key, defaultValueSupplier);
    }

    /**
     * get system config
     *
     * @param key key
     * @return config value
     */
    default Mono<String> getSystemConfigValue(String key) {
        return getSystemConfigValue(key, () -> "");
    }
}
