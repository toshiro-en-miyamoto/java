package se.stream.generator;

import java.util.Arrays;
import java.util.Set;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NineByNine implements IntSupplier {
   private int iField = 1;

   @Override
   public int getAsInt() {
      return iField++;
   }

   public static void main(String[] args) {

      System.out.println();
      IntStream.range(1, 10)
         .peek(p -> System.out.printf("%n%d", p))
         .flatMap(i -> IntStream.range(1, 10).map(j -> i * j))
         .forEach(k -> System.out.printf(" %02d", k));

      System.out.println();
      IntStream.generate(new NineByNine()).limit(9)
         .peek(p -> System.out.printf("%n%d", p))
         .flatMap(i -> IntStream.generate(new NineByNine()).limit(9).map(j -> i * j))
         .forEach(k -> System.out.printf(" %02d", k));

      System.out.println();
      IntStream.iterate(1, i -> ++i).limit(9)
         .peek(p -> System.out.printf("%n%d", p))
         .flatMap(i -> IntStream.iterate(1, j -> ++j).limit(9).map(j -> i * j))
         .forEach(k -> System.out.printf(" %02d", k));

      System.out.println();
      Set<Integer> nineSet = IntStream.of(1,2,3,4,5,6,7,8,9)
         .boxed()
         .collect(Collectors.toSet());
      nineSet.stream()
         .peek(p -> System.out.printf("%n%d", p))
         .flatMap(i -> nineSet.stream().map(j -> i * j))
         .forEach(k -> System.out.printf(" %02d", k));

      System.out.println();
      IntStream.of(1,2,3,4,5,6,7,8,9)
         .peek(p -> System.out.printf("%n%d", p))
         .flatMap(i -> IntStream.of(1,2,3,4,5,6,7,8,9).map(j -> i * j))
         .forEach(k -> System.out.printf(" %02d", k));

      System.out.println();
      int[] nine = {1,2,3,4,5,6,7,8,9};
      Arrays.stream(nine)
         .peek(p -> System.out.printf("%n%d", p))
         .flatMap(i -> Arrays.stream(nine).map(j -> i * j))
         .forEach(k -> System.out.printf(" %02d", k));

   }

}
