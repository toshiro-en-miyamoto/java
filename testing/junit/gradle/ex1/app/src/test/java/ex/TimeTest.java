package ex;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.chrono.JapaneseDate;
import java.time.chrono.JapaneseEra;
import java.time.temporal.ChronoField;

public class TimeTest {
    @Test
    void ja_date_from_grego()
    {
        var ww_date = LocalDate.of(2007, 12, 3);
        var ja_date = JapaneseDate.from(ww_date);

        assertEquals(JapaneseEra.HEISEI, ja_date.getEra());
        assertEquals(19, ja_date.get(ChronoField.YEAR_OF_ERA));
        assertEquals(12, ja_date.get(ChronoField.MONTH_OF_YEAR));
        assertEquals(3, ja_date.get(ChronoField.DAY_OF_MONTH));
        assertEquals("Japanese Heisei 19-12-03", ja_date.toString());
    }
}
