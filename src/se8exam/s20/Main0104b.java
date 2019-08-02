package se8exam.s20;
import java.lang.String;
import java.lang.Integer;
import java.math.BigDecimal;

public class Main0104b {
   public static void main(String[] args) {
      String str = "100";
      int val = Integer.parseInt(str);
      BigDecimal decimal = new BigDecimal(val);
      System.out.println(decimal.intValue());
   }
}
