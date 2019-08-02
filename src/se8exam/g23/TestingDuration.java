package se8exam.g23;

import java.time.Duration;
import java.time.Instant;

final class TestingDuration {
   public static void main(String[] args) {
      test1();
   }
   private static void test1() {
      Instant t0 = Instant.now();
      // do something
      Instant t1 = Instant.now();
      Duration duration1 = Duration.between(t0, t1);
      long second1 = duration1.getSeconds();
      int nanosec1 = duration1.getNano();
      System.out.format("%d.%09d%n", second1, nanosec1);
   }
}
