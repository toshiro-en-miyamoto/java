package se8exam.s13;

public class Sample1061 {
   public static void main(String[] args) {
      int[] array = {1, 2, 3, 4, 5};
      int key = 3;
      int cnt = 0;
      for (int i : array) {
         if (i != key) {
            continue;
            // cnt++;
         }
      }
      System.out.println(cnt);
   }
}
