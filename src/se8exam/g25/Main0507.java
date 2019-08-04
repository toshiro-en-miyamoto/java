package se8exam.g25;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

final class Main0507 {
   static void tryCatch() {
      FileReader in = null;
      FileWriter out = null;

      try {
         in = new FileReader("in.txt");
         out = new FileWriter("out.txt");
         // ファイルの読み書き
      } catch (IOException e) {
         // ファイルのオープンおよび読み書きに対する例外処理
      } finally {
         try {
            if (in != null) {
               in.close(); // FileReaderのクローズ処理
            }
            if (out != null) {
               out.close(); // FileWriterのクローズ処理
            }
         } catch (IOException e) {
            // closeメソッドに対する例外処理
         }
      }
   }
   static void tryWithResource() {
      try (FileReader in = new FileReader("in.txt");
         FileWriter out = new FileWriter("out.txt")) {
         // ファイルの読み書き
      } catch (IOException e) {
         // 例外処理
      }
   }
}