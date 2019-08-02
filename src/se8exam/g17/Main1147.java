package se8exam.g17;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

final class Main1147 {

   public static void main(String[] args) {
      /**
      List<Integer> list = Arrays.asList(1, 2, 3);
      UnaryOperator<Double> op = s -> s + 1.5;
      list.replaceAll(op);
      list.forEach(n -> System.out.print(n + " "));
      **/
      optionA();
      optionB();
   }
   private static void optionA() {
      // displaying [2.5 3.5 4.5]
      List<Double> list = Arrays.asList(1.0, 2.0, 3.0);
      UnaryOperator<Double> op = s -> s + 1.5;
      list.replaceAll(op);
      list.forEach(n -> System.out.print(n + " "));
   }
   private static void optionB() {
      // displaying [2 3 4]
      List<Integer> list = Arrays.asList(1, 2, 3);
      UnaryOperator<Integer> op = s -> s + 1;
      list.replaceAll(op);
      list.forEach(n -> System.out.print(n + " "));
   }
}
