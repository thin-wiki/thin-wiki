package wiki.thin.install.bean;

import lombok.Data;

/**
 * @author beldon
 */
@Data
public class DatabaseData {
    private String dbHost;
    private String dbPort;
    private String dbName;
    private String dbUser;
    private String dbPass;
}
