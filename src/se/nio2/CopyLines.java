package se.nio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

final class CopyLines {

   private static void oldStyle() {
      final String INPUT_FILENAME  = "data/basicio/xanadu.txt";
      final String OUTPUT_FILENAME = "data/basicio/xanadu-copied.txt";
      BufferedReader reader = null;
      PrintWriter writer = null;

      try {
         reader = new BufferedReader(
                  new InputStreamReader(
                  new FileInputStream(INPUT_FILENAME), "UTF-8"));
         writer = new PrintWriter(
                  new BufferedWriter(
                  new OutputStreamWriter(
                  new FileOutputStream(OUTPUT_FILENAME, false), StandardCharsets.UTF_8)));
         String line;
         while ((line = reader.readLine()) != null) {
            writer.println(line);
         }
      } catch (FileNotFoundException e) {
         System.err.println("A file for CopyCharacters not found.");
      } catch (IOException e) {
         System.err.println("Failed to copy characters.");
      } finally {
         try {
            if (reader != null ) { reader.close(); }
            if (writer != null ) { writer.close(); }
         } catch (IOException e) {
            System.err.println("Failed to close an output stream.");
         }
      }
   }

   private static void usingNio2Stream1() {
      Path pathData = Paths.get("data", "basicio");
      Path pathFrom = pathData.resolve("xanadu.txt");
      Path pathTo   = pathData.resolve("xanadu-copied.txt");

      try (
            BufferedReader reader =
                  Files.newBufferedReader(pathFrom, StandardCharsets.UTF_8);
            PrintWriter writer =
                  new PrintWriter(
                  Files.newBufferedWriter(pathTo, StandardCharsets.UTF_8, StandardOpenOption.APPEND));
         )
      {
         reader
            .lines()
            .forEach(writer::println);
      } catch (FileNotFoundException e) {
         System.err.println("A file for CopyCharacters not found.");
      } catch (IOException e) {
         System.err.println("Failed to copy characters.");
      }
   }

   private static void usingNio2Stream2() {
      Path pathData = Paths.get("data", "basicio");
      Path pathFrom = pathData.resolve("xanadu.txt");
      Path pathTo   = pathData.resolve("xanadu-copied.txt");

      try (
            Stream<String> stream =
                  Files.lines(pathFrom, StandardCharsets.UTF_8);
            PrintWriter writer =
                  new PrintWriter(
                  Files.newBufferedWriter(pathTo, StandardCharsets.UTF_8, StandardOpenOption.APPEND));
         )
      {
         stream.forEach(writer::println);
      } catch (FileNotFoundException e) {
         System.err.println("A file for CopyCharacters not found.");
      } catch (IOException e) {
         System.err.println("Failed to copy characters.");
      }
   }

   public static void main(String[] args) {
      oldStyle();
      usingNio2Stream1();
      usingNio2Stream2();
   }

}
