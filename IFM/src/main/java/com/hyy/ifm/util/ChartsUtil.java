package com.hyy.ifm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: 毛椅俊
 * \* Date: 2017/12/26
 * \* Time: 9:55
 * \
 */
public class ChartsUtil {

    public static String getDays() {
        String temp = " SELECT curdate() as click_date union all ";
        for(int i=1; i<30; i++) {
            temp += " SELECT date_sub(curdate(), interval " + i + " day) as click_date union all ";
        }
        temp += " SELECT date_sub(curdate(), interval 30 day) as click_date ";
        return temp;
    }

    public static String getDays(String dateFrom, String dateTo) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format.parse(dateFrom);
        Date date2 = format.parse(dateTo);
        int a = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        String temp = " SELECT date('"+ dateTo +"') as click_date union all ";
        for(int i=1; i<a; i++) {
            temp += " SELECT date_sub(date('"+ dateTo +"'), interval " + i + " day) as click_date union all ";
        }
        temp += " SELECT date_sub(date('"+ dateTo +"'), interval "+ a +" day) as click_date ";
        return temp;
    }

}
