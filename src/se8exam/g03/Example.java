package se8exam.g03;

public class Example {
   public static void main(String[] args) {
      int x = 6;
      int y = -2;
      assert (y >= 1) : "y=" + y ; // : "Invalid Denominator";
      // if (y < 1) {
      //    throw new Error("Invalid Denominator");
      // }

      System.out.println(x / y);
   }
}
/*
「-3」が表示される
「0」が表示される
何も表示されない
AssertionErrorがスローされる
*/