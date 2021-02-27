package easyon.alphacalcolutions.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilTest {

    @Test
    void daysBetween() {
        assertEquals(15, DateUtil.daysBetween(LocalDate.parse("2020-12-12"), LocalDate.parse("2020-12-26")));
        assertEquals(37, DateUtil.daysBetween(LocalDate.parse("2020-11-01"), LocalDate.parse("2020-12-07")));
        assertEquals(9, DateUtil.daysBetween(LocalDate.parse("2020-12-24"), LocalDate.parse("2021-01-01")));
    }

    @Test
    void businessDaysBetween() {
        assertEquals(10, DateUtil.businessDaysBetween(LocalDate.parse("2020-12-12"), LocalDate.parse("2020-12-26")));
        assertEquals(26, DateUtil.businessDaysBetween(LocalDate.parse("2020-11-01"), LocalDate.parse("2020-12-07")));
        assertEquals(7, DateUtil.businessDaysBetween(LocalDate.parse("2020-12-24"), LocalDate.parse("2021-01-01")));
    }
}