package org.z1key.projects.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by User on 02.02.2017.
 */
public class Validator {

    public static boolean validateDate(String date, String format) {
        if (date == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            //if not valid, it will throw ParseException
            Date d = sdf.parse(date);
        } catch (ParseException e) {

            e.printStackTrace();
            return false;
        }
        return true;
    }
}
