package se.exception;

final class Finally {

   public static void main(String[] args) {
      try {
         Integer.parseInt("xyz");
      } catch (NumberFormatException ex) {
         System.out.println("catching Number Format Exception ...");
         System.err.println(args[1]);
      } finally {
         System.out.println("finally");
      }
      System.out.println("after try");
   }

}
