package se8exam.s03;

public class Main23 {
   public static void main(String[] args) {
      SubSample s1 = new SubSample(100);
      SubSample s2 = new SubSample("sample", 200, 100);
      System.out.println(s1.name + ", " + s1.num + ", " + s1.price);
      System.out.println(s2.name + ", " + s2.num + ", " + s2.price);
   }
}
/*
sample 200 100
sample 200 100
null 0 100
sample 200 100
SubSampleクラスの3行目だけでコンパイルエラーになる
SubSampleクラスの8行目だけでコンパイルエラーになる
SubSampleクラスの3行目と8行目の両方でコンパイルエラーになる
*/