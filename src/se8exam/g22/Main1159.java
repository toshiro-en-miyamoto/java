package se8exam.g22;
import java.util.function.BiFunction;

final class Main1159 {
   public static void main(String[] args) {
      BiFunction<Integer, Double, Integer> f = (n1, n2) -> Double.valueOf(n1 + n2).intValue();
      System.out.println(f.apply(1,  2.5));
      BiFunction<Integer, Double, Double> f2 = (n1, n2) -> n1 + n2;
      System.out.println(f2.apply(1,  2.5));
   }
}
