package wiki.thin.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import wiki.thin.entity.AppConfig;
import wiki.thin.repo.AppConfigAutoRepo;
import wiki.thin.service.AppConfigService;

import java.util.function.Supplier;

/**
 * @author beldon
 */
@Service
@AllArgsConstructor
public class AppConfigServiceImpl implements AppConfigService {

    private final AppConfigAutoRepo appConfigAutoRepo;

    @Override
    public Mono<AppConfig> updateConfig(String type, String key, String value, String description) {
        AppConfig appConfig = new AppConfig();
        appConfig.setType(type);
        appConfig.setKey(key);

        final Mono<AppConfig> configMono = appConfigAutoRepo.findByTypeAndKey(type, key);
        return configMono.defaultIfEmpty(appConfig)
                .flatMap(v -> {
                    v.setValue(value);
                    v.setDescription(description);
                    return appConfigAutoRepo.save(v);
                });
    }

    @Override
    public Mono<AppConfig> getConfig(String type, String key, Supplier<String> defaultValueSupplier) {

        return appConfigAutoRepo.findByTypeAndKey(type, key)
                .switchIfEmpty(create(type, key, defaultValueSupplier.get()));

    }

    private Mono<AppConfig> create(String type, String key, String defaultValue) {
        AppConfig appConfig = new AppConfig();
        appConfig.setType(type);
        appConfig.setKey(key);
        appConfig.setValue(defaultValue);
        return appConfigAutoRepo.save(appConfig);
    }
}
