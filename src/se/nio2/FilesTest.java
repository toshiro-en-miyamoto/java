package se.nio2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;

class FilesTest {
   public static void main(String... args) {
      Path[] files = {
         Paths.get("FilesTest.class"),
         Paths.get("FilesTest.java")
      };

      Stream.of(files)
         .filter(p -> p.toString().endsWith("java"))
         .forEach(p -> {
            try {
               Files.readAllLines(p).stream().forEach(System.out::println);
            } catch(IOException e) {
               e.printStackTrace();
            }
         });
   }

}
