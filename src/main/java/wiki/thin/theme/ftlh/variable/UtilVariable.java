package wiki.thin.theme.ftlh.variable;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import wiki.thin.common.util.DateUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Beldon.
 */
@Service
@Lazy(value = false)
public class UtilVariable extends BaseVariable {
    private static final int SHORT_TIMESTAMP_LENGTH = 10;
    private static final int RADIX = 1024;
    private static final int COLOR_RADIX = 256;
    private static final Random RANDOM = new Random();

    public UtilVariable() {
        super("utilVar");
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

    public String getPrintSize(long size) {
        double value = (double) size;
        if (value < RADIX) {
            return value + "B";
        } else {
            value = BigDecimal.valueOf(value / RADIX).setScale(2, RoundingMode.DOWN).doubleValue();
        }
        if (value < RADIX) {
            return value + "KB";
        } else {
            value = BigDecimal.valueOf(value / RADIX).setScale(2, RoundingMode.DOWN).doubleValue();
        }
        if (value < RADIX) {
            return value + "MB";
        } else {
            value = BigDecimal.valueOf(value / RADIX).setScale(2, RoundingMode.DOWN).doubleValue();
            return value + "GB";
        }
    }

    public String fromToday(Date date) {
        return DateUtils.fromToday(date);
    }

    public String randColorCode() {
        String r;
        String g;
        String b;

        r = Integer.toHexString(RANDOM.nextInt(COLOR_RADIX)).toUpperCase();
        g = Integer.toHexString(RANDOM.nextInt(COLOR_RADIX)).toUpperCase();
        b = Integer.toHexString(RANDOM.nextInt(COLOR_RADIX)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return r + g + b;
    }

}
