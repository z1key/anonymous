package org.z1key.projects.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by User on 02.02.2017.
 */
public class Parser {

    public static Date parseDate(String dateString) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate localDate = LocalDate.parse(dateString, dtf);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public static Date parseDate(String dateString, String format) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);

        LocalDate localDate = LocalDate.parse(dateString, dtf);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
