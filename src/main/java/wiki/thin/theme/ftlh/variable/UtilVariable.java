package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Beldon.
 */
@Service
@Lazy(value = false)
public class UtilVariable extends BaseVariable {
    private static final int SHORT_TIMESTAMP_LENGTH = 10;

    public UtilVariable() {
        super("util");
    }

    public String dataFormat(Long timestamp, String format) {
        if (timestamp == null) {
            return "";
        }
        long cleanTimeStamp = timestamp;
        if (String.valueOf(timestamp).length() == SHORT_TIMESTAMP_LENGTH) {
            cleanTimeStamp = timestamp * 1000;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date(cleanTimeStamp));
    }

}
