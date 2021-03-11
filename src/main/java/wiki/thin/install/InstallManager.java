package wiki.thin.install;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import wiki.thin.install.bean.AdminAccount;
import wiki.thin.install.bean.DatabaseData;
import wiki.thin.install.bean.Website;
import wiki.thin.service.PasswordService;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import java.util.UUID;

/**
 * @author beldon
 */
@Service
@Profile("install")
@Slf4j
public class InstallManager {

    private static final String CHECK_RESULT_TOKEN_KEY = "token_check_result";
    private final FlywayProperties flywayProperties;
    private final PasswordService passwordService;
    private JdbcTemplate jdbcTemplate;

    public InstallManager(FlywayProperties flywayProperties, PasswordService passwordService) {
        this.flywayProperties = flywayProperties;
        this.passwordService = passwordService;
    }

    void checkAndCreateAuthFile() throws IOException {
        final Path authFilePath = getAuthFilePath();
        if (authFilePath.toFile().exists()) {
            return;
        }
        final OutputStream outputStream = Files.newOutputStream(authFilePath);
        try (outputStream) {
            outputStream.write(UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * 检查 token
     *
     * @return
     */
    boolean checkToken(String token, HttpServletRequest request) {
        final Path authFilePath = getAuthFilePath();
        try (InputStream inputStream = Files.newInputStream(authFilePath)) {
            String fileToken = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            final boolean checkResult = token.equals(fileToken);
            request.getSession().setAttribute(CHECK_RESULT_TOKEN_KEY, checkResult);
            return checkResult;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("get file token error", e);
            return false;
        }
    }

    boolean passCheckToken(HttpServletRequest request) {
        final Boolean checkResult = (Boolean) request.getSession().getAttribute(CHECK_RESULT_TOKEN_KEY);
        return Boolean.TRUE.equals(checkResult);
    }

    public void removeAuthFile() throws IOException {
        Files.deleteIfExists(getAuthFilePath());
    }

    Path getAuthFilePath() {
        final Path path = Path.of(System.getProperty("user.dir"), "lib", "install.txt");
        path.getParent().toFile().mkdirs();
        return path;
    }

    boolean checkConnect(DatabaseData databaseData) {
        String jdbc = "jdbc:mysql://" + databaseData.getDbHost() + ":"
                + databaseData.getDbPort() + "/"
                + databaseData.getDbName() + "?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbc);
        hikariConfig.setUsername(databaseData.getDbUser());
        hikariConfig.setPassword(databaseData.getDbPass());

        try {
            var dataSource = new HikariDataSource(hikariConfig);
            jdbcTemplate = new JdbcTemplate(dataSource);
            jdbcTemplate.execute("select 1");
            migrate(dataSource);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    void createConfigFile(DatabaseData databaseData) throws IOException {
        final Path configFilePath = getConfigFilePath();
        Files.deleteIfExists(configFilePath);

        String jdbc = "jdbc:mysql://" + databaseData.getDbHost() + ":"
                + databaseData.getDbPort() + "/"
                + databaseData.getDbName() + "?characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";

        Properties properties = new Properties();
        properties.setProperty("spring.datasource.url", jdbc);
        properties.setProperty("spring.datasource.username", databaseData.getDbUser());
        properties.setProperty("spring.datasource.password", databaseData.getDbPass());

        final OutputStream outputStream = Files.newOutputStream(configFilePath);
        try (outputStream) {
            properties.store(outputStream, "install init");
        }
    }

    void setWebsiteConfig(Website website) {

        jdbcTemplate.execute("delete from app_config where `type` = 'system' and `key` = 'website_name'");
        jdbcTemplate.execute("delete from app_config where `type` = 'system' and `key` = 'website_description'");
        jdbcTemplate.execute("delete from app_config where `type` = 'system' and `key` = 'website_keywords'");

        String nameSql = "INSERT INTO app_config (id, type, `key`, value) "
                + "VALUES (1101, 'system', 'website_name', '" + website.getName() + "');";
        String descriptionSql = "INSERT INTO app_config (id, type, `key`, value) "
                + "VALUES (1102, 'system', 'website_description', '" + website.getDescription() + "');";
        String keywordsSql = "INSERT INTO app_config (id, type, `key`, value) "
                + "VALUES (1103, 'system', 'website_keywords', '" + website.getKeyword() + "');";
        jdbcTemplate.execute(nameSql);
        jdbcTemplate.execute(descriptionSql);
        jdbcTemplate.execute(keywordsSql);
    }

    void setAccount(AdminAccount adminAccount) {
        jdbcTemplate.execute("delete from user;");
        final String encodePassword = passwordService.encode(adminAccount.getPassword());
        String sql = "INSERT INTO user (id, account, password) VALUES (1000, '" + adminAccount.getAccount() + "', '" + encodePassword + "');";
        jdbcTemplate.execute(sql);
    }

    private void migrate(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .cleanDisabled(flywayProperties.isCleanDisabled())
                .locations(flywayProperties.getLocations().toArray(new String[]{}))
                .table(flywayProperties.getTable())
                .baselineVersion(flywayProperties.getBaselineVersion())
                .validateOnMigrate(flywayProperties.isValidateOnMigrate())
                .baselineOnMigrate(flywayProperties.isBaselineOnMigrate())
                .load();
        flyway.migrate();
    }

    private static Path getConfigFilePath() {
        final Path path = Path.of(System.getProperty("user.dir"), "lib", "application.properties");
        path.getParent().toFile().mkdirs();
        return path;
    }

    public static boolean isInstalled() {
        return Files.exists(getConfigFilePath());
    }
}
