package se8exam.g09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.stream.Stream;

final class Main1124 {
   public static void main(String[] args) {
      Path dir = Paths.get(System.getProperty("user.home"), "data");
      try (Stream<Path> pathStream = Files.walk(dir)) {
         pathStream.forEach(path -> {  // line n1
            try {
               FileTime time = Files
                  .readAttributes(path, BasicFileAttributes.class)
                  .lastAccessTime();  // line n2
               System.out.println(path + ":" + time);
            } catch(IOException e) {
               e.printStackTrace();
            }
         });
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
