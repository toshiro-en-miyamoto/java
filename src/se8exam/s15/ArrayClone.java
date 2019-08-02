package se8exam.s15;

final class ArrayClone {

   public static void main(String[] args) {
      // p119
      int[] arrayA = { 1, 2, 3, 4 };
      int[] arrayB = arrayA.clone();
      System.out.println(arrayA == arrayB);
      for (int i : arrayB) {
         System.out.println(i);
      }
   }

}
