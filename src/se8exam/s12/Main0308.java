package se8exam.s12;

public class Main0308 {
   public static void main(String[] args) {
      Sample s1 = new Sample(10);
      Sample s2 = s1;
      s1 = new Sample(10);
      System.out.println(s1 == s2);
      System.out.println(s1.equals(s2));
   }
}
