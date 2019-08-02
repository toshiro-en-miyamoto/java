package se.immutable;

class Unboxing {

   public static void main(String[] args) {

      double d1 = 3.14;
      double d2 = d1;
      Double o1 = d1;
      Double o2 = d2;
      Double ox = o1;
      assert d1 == d2;
      assert o1 != o2;
      assert o1.equals(o2);
      assert o1 == ox;
      System.out.println("All that's finished");
   }

}
