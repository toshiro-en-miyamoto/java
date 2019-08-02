package se8exam.g21;

import java.time.LocalDate;
import java.time.Month;

final class Date1156 {
   public static void main(String[] args) {
      LocalDate christmasDay = LocalDate.of(2016, Month.DECEMBER, 25);
      LocalDate lastYear = christmasDay.minusYears(1);
      lastYear.plusDays(7);
      System.out.println(lastYear);
   }
}
