package se8exam.s20;

public class Main0104a {
   public static void main(String[] args) {
      java.lang.String str = "100";
      int val = java.lang.Integer.parseInt(str);
      java.math.BigDecimal decimal = new java.math.BigDecimal(val);
      System.out.println(decimal.intValue());
   }
}
