package se8exam.s09;

public class Main0610 {
   public static void main(String[] args) {
      Main0610 m = new Main0610();
      System.out.println(m.calc(2.0, 3));
      System.out.println(m.calc(2, 3.0));
   }
   private double calc(double a, int b) {
      return (a + b) / 2;
   }
   private double calc(int a, double b) {
      return (a + b) / 2;
   }
}
