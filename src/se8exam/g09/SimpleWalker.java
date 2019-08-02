package se8exam.g09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import static java.nio.file.FileVisitOption.*;

final class SimpleWalker {
   public static void main(String[] args) {
      Path dir = Paths.get(System.getProperty("user.home"), "data");
      try (Stream<Path> pathStream = Files.walk(dir, 1, FOLLOW_LINKS)) {
         pathStream.forEach(System.out::println);
      } catch(IOException e) {
      }
   }
}
