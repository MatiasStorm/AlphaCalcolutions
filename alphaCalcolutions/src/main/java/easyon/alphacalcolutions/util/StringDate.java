package easyon.alphacalcolutions.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringDate extends Date {
    public StringDate(String dateString) throws ParseException {
        super();
        String[] dateArray = dateString.split("-");
        int year = Integer.parseInt(dateArray[0]);
        this.setYear(year);
        int month = Integer.parseInt(dateArray[1]);
        this.setMonth(month);
        int date = Integer.parseInt(dateArray[2]);
        this.setDate(date);
    }
}
