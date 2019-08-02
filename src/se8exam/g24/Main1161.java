package se8exam.g24;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

public class Main1161 {
   public static void main(String[] args) {
      f1();
      f2();
   }
   private static void f1() {
      // UnaryOperator<Integer> op = n -> n * 2; // line n1
      UnaryOperator<Double> op = n -> n * 2.0; // line n1
      List<Double> list = Arrays.asList(1.5, 2.2, 3.5);
      list.stream()
         .filter(d -> d > 2.0)
         .map(d -> op.apply(d)) // line n2
         .forEach(n -> System.out.print(n + " "));
   }
   private static void f2() {
      UnaryOperator<Integer> op = n -> n * 2; // line n1
      List<Integer> list = Arrays.asList(1, 2, 3);
      list.stream()
         .filter(d -> d > 2)
         .map(d -> op.apply(d)) // line n2
         .forEach(n -> System.out.print(n + " "));
   }
}
