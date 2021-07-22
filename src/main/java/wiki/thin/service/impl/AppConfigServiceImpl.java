//package wiki.thin.service.impl;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import wiki.thin.entity.AppConfig;
//import wiki.thin.repo.AppConfigAutoRepo;
//import wiki.thin.service.AppConfigService;
//
//import java.util.Optional;
//import java.util.function.Supplier;
//
///**
// * @author beldon
// */
//@Service
//@AllArgsConstructor
//public class AppConfigServiceImpl implements AppConfigService {
//
//    private final AppConfigAutoRepo appConfigAutoRepo;
//
//    @Override
//    public void updateConfig(String type, String key, String value, String description) {
//        AppConfig appConfig = new AppConfig();
//        appConfig.setType(type);
//        appConfig.setKey(key);
//        appConfig.setValue(value);
//        appConfig.setDescription(description);
//
//
//        final Optional<AppConfig> configOptional = appConfigAutoRepo.findByTypeAndKey(type, key);
//        if (configOptional.isEmpty()) {
//            appConfigMapper.insertSelective(appConfig);
//            return;
//        }
//
//        appConfig.setId(configOptional.get().getId());
//        appConfigMapper.updateByIdSelective(appConfig);
//
//    }
//
//    @Override
//    public AppConfig getConfig(String type, String key, Supplier<String> defaultValueSupplier) {
//        final Optional<AppConfig> configOptional = appConfigMapper.findByTypeAndKey(type, key);
//        if (configOptional.isEmpty()) {
//            updateConfig(type, key, defaultValueSupplier.get(), null);
//            return getConfig(type, key);
//        }
//        return configOptional.get();
//    }
//
//}
