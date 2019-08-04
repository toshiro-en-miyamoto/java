package se8exam.g25;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

final class Main1165 {
   public static void main(String[] args) throws IOException {
      BufferedReader reader1 = null;
      try (BufferedReader reader2 = new BufferedReader(new FileReader("sample.txt"))) {
         reader2.lines()
            .forEach(line -> System.out.println(line));
        reader1 = reader2;
      }
      if (reader1.ready()) {
         reader1.lines()
            .forEach(line -> System.out.println(line));
      }
   }
}