package se8exam.s06;

public class Sample1031 {
   void test() throws Exception {
      System.out.println("test");
   }
   void hoge() throws RuntimeException {
      System.out.println("hoge");
   }
   public static void main1(String[] args) {
      Sample1031 s = new Sample1031();
      // s.test();
      s.hoge();
   }
   public static void main(String[] args) {
      Sample1031 s = new Sample1031();
      try {
         s.test();
      } catch(Exception e) {
         // do something
      }
      s.hoge();
   }
   public static void main2(String[] args) throws Exception {
      Sample1031 s = new Sample1031();
      s.test();
      s.hoge();
   }
}
