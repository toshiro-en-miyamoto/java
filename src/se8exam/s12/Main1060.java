package se8exam.s12;

public class Main1060 {
   public static void main(String[] args) {
      A a = new C();
      C b = new C();
      D c = new D();
      if (a instanceof C) System.out.print("a");
      if (b instanceof A) System.out.print("b");
      if (c instanceof A) System.out.print("c");
   }
}
