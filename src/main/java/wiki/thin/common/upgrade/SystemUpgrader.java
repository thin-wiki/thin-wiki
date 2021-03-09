package wiki.thin.common.upgrade;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.update4j.Archive;
import org.update4j.Configuration;
import org.update4j.UpdateOptions;
import wiki.thin.common.env.EnvManager;
import wiki.thin.common.properties.AppUpgradeProperties;
import wiki.thin.common.util.JsonUtils;
import wiki.thin.common.util.VersionUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author beldon
 */
@Service
public class SystemUpgrader {

    private static final String UPDATE_FILE_NAME = "lib/update.zip";

    private final AppUpgradeProperties appUpgradeProperties;
    private final RestTemplate restTemplate;
    private volatile boolean installed = false;

    public SystemUpgrader(AppUpgradeProperties appUpgradeProperties) {
        this.appUpgradeProperties = appUpgradeProperties;
        restTemplate = new RestTemplate();
    }

    /**
     * 判断是否有新版本
     *
     * @return 是否有新版本
     */
    public boolean hasNewVersion() {
        final String localVersion = EnvManager.BUILD_INFO.getVersion();
        return VersionUtils.isNewVersion(localVersion, getNewestVersion());
    }

    /**
     * 下载最新的
     */
    public void downloadNewest() throws IOException {
        download(getNewestVersion());
    }

    public void download(String version) throws IOException {
        String configFileUri = appUpgradeProperties.getBaseUrl()
                + appUpgradeProperties.getVersionBasePath() + "/"
                + version
                + "/"
                + appUpgradeProperties.getVersionConfigFile();
        URL url = new URL(configFileUri);
        final InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
        try (inputStreamReader) {
            Configuration config = Configuration.read(inputStreamReader);
            config.update(UpdateOptions.archive(getUpdateFilePath()));
        }
    }

    public void install() throws IOException {
        Archive.read(getUpdateFilePath()).install();
        installed = true;
    }

    /**
     * 判断是否已经下载
     *
     * @return
     */
    public boolean isDownloaded() {
        return Files.exists(getUpdateFilePath());
    }

    /**
     * 获取最新版本
     *
     * @return 最新版本
     */
    public String getNewestVersion() {
        String url = appUpgradeProperties.getBaseUrl() + appUpgradeProperties.getNewestVersionCheckUrl();
        final String newestVersionStr = restTemplate.getForObject(url, String.class);
        final NewestVersion newestVersion = JsonUtils.parse(newestVersionStr, NewestVersion.class);
        return newestVersion.getVersion();
    }

    public boolean isInstalled() {
        return installed;
    }

    private Path getUpdateFilePath() {
        final Path updateFilePath = Path.of(System.getProperty("user.dir"), UPDATE_FILE_NAME);
        updateFilePath.getParent().toFile().mkdirs();
        return updateFilePath;
    }
}
