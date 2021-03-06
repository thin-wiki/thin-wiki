package wiki.thin.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Beldon
 */
public class DateUtils {

    public static Integer currentYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.YEAR);
    }

    public static Integer currentMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.MONTH);
    }

    public static Integer currentDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
