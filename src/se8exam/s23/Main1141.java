package se8exam.s23;

public class Main1141 {
   public static void main(String[] args) {
      Item a = new Item();
      Item b = new Item();
      Item c = new Item();
      a = c;
      c = b;
      b = null; // line n1
      // do something
      assert a == c;
   }
}
