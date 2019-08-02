package se8exam.g23;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

final class TestingPeriod {
   public static void main(String[] args) {
      test1();
      test2();
      test3();
   }
   private static void test1() {
      LocalDate sapporo = LocalDate.of(1972, 2, 3);
      LocalDate nagano  = LocalDate.of(1998, 2, 7);
      Period period1 = Period.between(sapporo, nagano);
      assert period1.getYears() == 26;
      assert period1.getMonths() == 0;
      assert period1.getDays() == 4;
      assert "P26Y4D".equals(period1.toString());
   }
   private static void test2() {
      LocalDate start2 = LocalDate.of(2016, 2, 1);
      LocalDate end2 = LocalDate.of(2016, 3, 1);
      Period period2 = Period.between(start2, end2);
      assert period2.getYears() == 0;
      assert period2.getMonths() == 1;
      assert period2.getDays() == 0;
      assert "P1M".equals(period2.toString());
      long days = ChronoUnit.DAYS.between(start2, end2);
      assert days == 29;
   }
   private static void test3() {
      LocalDate start2 = LocalDate.of(2016, 2, 1);
      LocalDate end2 = LocalDate.of(2016, 3, 1);
      try {
         Duration duration2 = Duration.between(start2, end2);
         assert duration2.toDays() == 29;
      } catch(UnsupportedTemporalTypeException e) {
         System.err.println("Oops - test3()");
      }
   }
}
