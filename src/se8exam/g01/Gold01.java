package se8exam.g01;

import java.util.Arrays;

public class Gold01 {
   public static void main(String[] args) {
      Arrays.asList(1, 2, 3).stream()
         .map(x -> x + 1) // line n1
         .peek(System.out::print) // line n2
         .filter(x -> true)
         .count(); // line n3
   }
}
