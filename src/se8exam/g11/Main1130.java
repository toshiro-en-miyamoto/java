package se8exam.g11;

final class Main1130 {
   public static void main(String[] args) {
      test();
   }
   @SuppressWarnings({"rawtypes", "unchecked"})
   private static void test() {
      Foo<String> fs = new Foo<>();
      Foo f = new Foo();  // line n1
      fs.set("John");
      f.set(1);  // line n2
      System.out.print(f.get() + ":" + fs.get());
   }
}
