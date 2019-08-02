package se8exam.s19;

public class Main1113a {
   static int num; // int num;
   private static void test() {
      num++;
      System.out.println(num);
   }
   public static void main(String[] args) {
      Main1113a.test();
      Main1113a.test();
   }
}
