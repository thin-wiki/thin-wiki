package wiki.thin.common.upgrade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author beldon
 */
@Data
public class NewestVersion {
    private String version;
    @JsonProperty("build_time")
    private Date buildTime;
}
