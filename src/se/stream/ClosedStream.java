package se.stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ClosedStream {

   @SuppressWarnings("resource") // prevents "reader1 is never closed" warning
   public static void main(String[] args) throws IOException {
      BufferedReader reader1 = null;
      try (
         BufferedReader reader2 = new BufferedReader(new FileReader("src/se/stream/ClosedStream.java"))
      ) {
         reader2.lines()
            .forEach(line -> System.out.println(line));
         reader1 = reader2;
      }
      if (reader1.ready()) { // runtime exception "IOException: Stream closed"
         reader1.lines()
            .forEach(line -> System.out.println(line));
      }
   }
}
