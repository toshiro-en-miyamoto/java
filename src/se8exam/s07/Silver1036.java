package se8exam.s07;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Silver1036 {
   public static void main(String[] args) {
      LocalDate date1 = LocalDate.now();
      LocalDate date2 = LocalDate.of(2015, 8, 29);
      LocalDate date3 = LocalDate.parse("2015-08-29", DateTimeFormatter.ISO_DATE);
      System.out.println(date1);
      System.out.println(date2);
      System.out.println(date3);
   }
}
