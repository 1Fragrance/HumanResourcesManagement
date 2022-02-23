package hrm.helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateHelper {
    static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String getUTCdatetimeAsString() {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        final String utcTime = sdf.format(new Date());

        return utcTime;
    }

    public static Date getUTCdatetimeAsDate() {
        return stringDateToDate(getUTCdatetimeAsString());
    }

    public static Date stringDateToDate(String StrDate) {
        Date dateToReturn = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

        try {
            dateToReturn = (Date)dateFormat.parse(StrDate);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return dateToReturn;
    }
}
