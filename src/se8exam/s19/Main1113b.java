package se8exam.s19;

public class Main1113b {
   int num;
   private void test() {
      num++;
      System.out.println(num);
   }
   public static void main(String[] args) {
      Main1113b instance = new Main1113b();
      instance.test();
      instance.test();
   }
}
