package easyon.alphacalcolutions.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DateUtil {

    public static int daysBetween(LocalDate startDate, LocalDate endDate){
        /**
         * Returns days between startDate and endDate inclusive.
         */
        return (int) ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    public static int businessDaysBetween(LocalDate startDate, LocalDate endDate){
        /**
         * Returns business days between startDate and endDate inclusive.
         */
        int daysWorked = (int) ChronoUnit.DAYS.between(startDate, endDate) +1;

        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        long businessDays = Stream.iterate(startDate, date -> date.plusDays(1)).limit(daysWorked)
                .filter(isWeekend.negate()).count();
        return (int) businessDays;
    }
}
