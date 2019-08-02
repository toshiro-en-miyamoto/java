package se8exam.g10;

import java.io.FileInputStream;
import java.io.InputStreamReader;

class Main1129 {
   public static void main(String[] args) {
      try ( FileInputStream in = new FileInputStream("data/cert.txt");
            InputStreamReader reader = new InputStreamReader(in)) {
         int ch;
         while(reader.ready()) {
            reader.skip(3);  // line n1
            ch = reader.read();  // line n2
            System.out.print((char)ch);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
