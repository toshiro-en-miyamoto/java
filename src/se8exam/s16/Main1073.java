package se8exam.s16;

public class Main1073 {
   public static void main(String[] args) {
      try {
         hello();
      } catch(Exception e) {
         System.out.print("A");
      } finally {
         System.out.print("B");
      }
      System.out.print("C");
   }
   public static void hello() throws Exception {
      try {
         throw new Exception();
      } finally {
         System.out.print("D");
      }
   }
}
