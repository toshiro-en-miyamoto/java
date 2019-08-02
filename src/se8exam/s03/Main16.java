package se8exam.s03;

public class Main16 {
   public static void main(String[] args) {
      A a = new B();
      assert a != null;
   }
}
/*
「A」と表示される
「B」と表示される
「A」「B」と表示される
「B」「A」と表示される
コンパイルエラーが発生する
実行時に例外がスローされる
*/