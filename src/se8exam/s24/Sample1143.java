package se8exam.s24;

public class Sample1143 {
   public static void main(String[] args) {
      // byte a = 1; // OK
      // short a = 1; // OK
      // String a = "1"; // error - not match to '1'
      // long a = 1; // error
      // double a = 1; // error
      // @SuppressWarnings("deprecation")
      // Integer a = new Integer("1"); // OK
      Integer a = Integer.valueOf("1");
      switch (a) {
      case 1:
         System.out.println("A");
         break;
      // case "1": // error type mismatch
      default:
         System.out.println("B");
      }
   }
}
