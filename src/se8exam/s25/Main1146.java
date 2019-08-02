package se8exam.s25;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main1146 {
   public static void main(String[] args) {
      LocalDateTime date = LocalDateTime.of(2015, 9, 15, 1, 1);
      date.plusDays(30);
      date.plusMonths(1);
      System.out.println(date.format(DateTimeFormatter.ISO_DATE));
      date_time1();
      date_time2();
      date_time3();
   }
   private static void date_time1() {
      LocalDate date = LocalDate.of(2015, 9, 15);
      date = date.plusDays(30);
      date = date.plusMonths(1);
      System.out.println(date.format(DateTimeFormatter.ISO_DATE));
   }
   private static void date_time2() {
      LocalDate date = LocalDate.of(2015, 9, 15);
      date = date.plusDays(30).plusMonths(1);
      System.out.println(date.format(DateTimeFormatter.ISO_DATE));
   }
   private static void date_time3() {
      LocalDate date = LocalDate.of(2020, 2, 15);
      date = date.plusDays(29);
      assert date.equals(LocalDate.of(2020, 3, 15));
   }
}
