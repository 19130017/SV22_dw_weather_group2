package vn.dtbinh1511.sv22_dw_weather_group2.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateServices {
    private ZonedDateTime time = ZonedDateTime.now();

    public DateServices() {
    }

    public String getYear() {
        return time.format(DateTimeFormatter.ofPattern("yyyy"));
    }

    public String getMonth() {
        return time.format(DateTimeFormatter.ofPattern("MM"));
    }

    public String getDay() {
        return time.format(DateTimeFormatter.ofPattern("dd"));
    }

    public String getDate() {
        return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getDateWithDelimited() {
        return time.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public String getTime() {
        return time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public int getHour() {
        return Integer.parseInt(time.format(DateTimeFormatter.ofPattern("HH")));
    }

    public String getMinutes() {
        return time.format(DateTimeFormatter.ofPattern("mm"));
    }

    public String getZones() {
        return String.valueOf(time.getZone());
    }

    public Date getDateCrawl() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getFileName() {
        return getHour() + "h_" + getDateWithDelimited() + ".csv";
    }

}
