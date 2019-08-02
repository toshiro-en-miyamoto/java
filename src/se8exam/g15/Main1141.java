package se8exam.g15;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

final class Main1141 {
   public static void main(String[] args) {
      Function<Integer, Integer> f = n -> n;
      Stream<Integer> s = Arrays.asList(7, 3, 5).stream();
      System.out.println(
         // s.min() // min() requires Comparator
         // s.map(n -> n).min() // min() requires Comparator
         // s.map(Integer::min).get() // map() requires Function
//         s.min(Comparator.comparing(n -> n)).get()
         // s.min(Comparator.comparing(Function.identity())).get()
         s.min(Comparator.comparing(f)).get()
      );
      minInt();
   }
   static void minInt() {
      IntStream s = IntStream.of(7, 3, 5);
      System.out.println(
         s.min().getAsInt()
      );
   }
}
