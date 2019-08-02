package se8exam.s07;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateTimeFormatterSample {
   static void parse() throws DateTimeParseException {
      LocalDate ld = LocalDate.of(1972, 2, 3);

      DateTimeFormatter
      formatter = DateTimeFormatter.BASIC_ISO_DATE;
      LocalDate
      parsedDate = LocalDate.parse("19720203", formatter);
      assert ld.equals(parsedDate);

      formatter = DateTimeFormatter.ISO_LOCAL_DATE;
      parsedDate = LocalDate.parse("1972-02-03", formatter);
      assert ld.equals(parsedDate);

      parsedDate = LocalDate.parse("1972-02-03");
      assert ld.equals(parsedDate);

      formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
      parsedDate = LocalDate.parse("1972/02/03", formatter);
      assert ld.equals(parsedDate);
   }
   static void format() throws DateTimeException {
      LocalDate ld = LocalDate.of(1972, 2, 3);

      DateTimeFormatter
      formatter = DateTimeFormatter.BASIC_ISO_DATE;
      String
      formattedDate = ld.format(formatter);
      assert "19720203".equals(formattedDate);

      formatter = DateTimeFormatter.ISO_LOCAL_DATE;
      formattedDate = ld.format(formatter);
      assert "1972-02-03".equals(formattedDate);

      formattedDate = ld.toString();
      assert "1972-02-03".equals(formattedDate);

      formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
      formattedDate = ld.format(formatter);
      assert "1972/02/03".equals(formattedDate);

      formatter = DateTimeFormatter
                  .ofPattern("yyyy年MM月dd日(E)", Locale.JAPAN);
      formattedDate = ld.format(formatter);
      assert "1972年02月03日(木)".equals(formattedDate);
   }
   public static void main(String[] args) {
      try {
         parse();
         format();
      } catch(DateTimeParseException e) {
         e.printStackTrace();
      } catch(DateTimeException e) {
         e.printStackTrace();
      }
   }
}
