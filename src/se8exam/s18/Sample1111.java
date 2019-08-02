package se8exam.s18;

public class Sample1111 {
   public static void main(String[] args) {
      String str = "apple";
      String[] array = {"a","p","p","l","e"};
      String result = "";
      for (String val : array) {
         result = result + val;
      }
      boolean a = str == result;
      boolean b = str.equals(result);
      System.out.println(a + ":" + b);
   }
}
