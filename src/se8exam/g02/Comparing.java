package se8exam.g02;

import java.util.Comparator;

public class Comparing {

   public static void main(String[] args) {
      Integer i1 = 1;
      Integer i2 = 2;
      assert(i1.compareTo(i2) < 0);
      assert(i2.compareTo(i1) > 0);

      Comparator<Integer> c = new Comparator<Integer>() {
         public int compare(Integer i1, Integer i2) {
            return i2.compareTo(i1);
         }
      };
      assert(c.compare(i1, i2) > 0);
   }

}
