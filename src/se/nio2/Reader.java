package se.nio2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Reader {

   public static void main(String... args) throws IOException {
      try (
         BufferedReader reader = new BufferedReader(new FileReader("src/se/nio2/Reader.java"))
      ) {
         reader.lines()
            .forEach(line -> System.out.println(line));
      }
   }
}
