package wiki.thin.service;

import wiki.thin.constant.ConfigConstant;
import wiki.thin.entity.AppConfig;

import java.util.Optional;
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
    void updateConfig(String type, String key, String value, String description);

    /**
     * update config
     *
     * @param type  type
     * @param key   key
     * @param value value
     */
    default void updateConfig(String type, String key, String value) {
        updateConfig(type, key, value, null);
    }

    /**
     * update system config
     *
     * @param key         key
     * @param value       value
     * @param description description
     */
    default void updateSysConfig(String key, String value, String description) {
        updateConfig(ConfigConstant.CONFIG_TYPE_SYS, key, value, description);
    }

    /**
     * update system config
     *
     * @param key   key
     * @param value value
     */
    default void updateSysConfig(String key, String value) {
        updateSysConfig(key, value, null);
    }


    /**
     * get config
     *
     * @param type                 type
     * @param key                  key
     * @param defaultValueSupplier default value
     * @return app config
     */
    AppConfig getConfig(String type, String key, Supplier<String> defaultValueSupplier);

    /**
     * get config
     *
     * @param type type
     * @param key  key
     * @return app config
     */
    default AppConfig getConfig(String type, String key) {
        return getConfig(type, key, null);
    }

    /**
     * get system config
     *
     * @param key key
     * @return app config
     */
    default AppConfig getSystemConfig(String key) {
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
    default String getConfigValue(String type, String key, Supplier<String> defaultValueSupplier) {
        final AppConfig config = getConfig(type, key, defaultValueSupplier);
        if (config.getValue() == null) {
            final String defaultValue = defaultValueSupplier.get();
            updateConfig(type, key, defaultValue);
            return defaultValue;
        }
        return config.getValue();
    }

    /**
     * get config
     *
     * @param type type
     * @param key  key
     * @return config value
     */
    default Optional<String> getConfigValue(String type, String key) {
        return Optional.of(getConfigValue(type, key, () -> null));
    }

    /**
     * get system config
     *
     * @param key                  key
     * @param defaultValueSupplier default value supplier
     * @return config value
     */
    default String getSystemConfigValue(String key, Supplier<String> defaultValueSupplier) {
        return getConfigValue(ConfigConstant.CONFIG_TYPE_SYS, key, defaultValueSupplier);
    }

    /**
     * get system config
     *
     * @param key key
     * @return config value
     */
    default Optional<String> getSystemConfigValue(String key) {
        return Optional.of(getSystemConfigValue(key, () -> null));
    }
}
