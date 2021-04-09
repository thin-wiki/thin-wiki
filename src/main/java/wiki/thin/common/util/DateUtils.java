package wiki.thin.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Beldon
 */
public class DateUtils {

    private static final long ONE_MINUTE = 60;
    private static final long ONE_HOUR = 3600;
    private static final long ONE_DAY = 86400;
    private static final long TWO_DAY = ONE_DAY * 2;
    private static final long THREE_DAY = ONE_DAY * 3;
    private static final long ONE_MONTH = 2592000;
    private static final long ONE_YEAR = 31104000;

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

    public static String fromToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        long time = date.getTime() / 1000;
        long now = System.currentTimeMillis() / 1000;
        long ago = now - time;
        if (ago <= ONE_HOUR) {
            return ago / ONE_MINUTE + "分钟前";
        } else if (ago <= ONE_DAY) {
            return ago / ONE_HOUR + "小时" + (ago % ONE_HOUR / ONE_MINUTE) + "分钟前";
        } else if (ago <= TWO_DAY) {
            return "昨天" + calendar.get(Calendar.HOUR_OF_DAY) + "点" + calendar.get(Calendar.MINUTE) + "分";
        } else if (ago <= THREE_DAY) {
            return "前天" + calendar.get(Calendar.HOUR_OF_DAY) + "点" + calendar.get(Calendar.MINUTE) + "分";
        } else if (ago <= ONE_MONTH) {
            long day = ago / ONE_DAY;
            return day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点" + calendar.get(Calendar.MINUTE) + "分";
        } else if (ago <= ONE_YEAR) {
            long month = ago / ONE_MONTH;
            long day = ago % ONE_MONTH / ONE_DAY;
            return month + "个月" + day + "天前" + calendar.get(Calendar.HOUR_OF_DAY) + "点"
                    + calendar.get(Calendar.MINUTE) + "分";
        } else {
            long year = ago / ONE_YEAR;
            int month = calendar.get(Calendar.MONTH) + 1;
            return year + "年前" + month + "月" + calendar.get(Calendar.DATE) + "日";
        }

    }
}
