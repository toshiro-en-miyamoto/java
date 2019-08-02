package se.nio2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

final class CopyCharacters {
   private static final String INPUT_FILENAME  = "data/se.tutorials/basicio/xanadu.txt";
   private static final String OUTPUT_FILENAME = "data/se.tutorials/basicio/xanadu-copied.txt";

   private static void oldStyleTry() {
      InputStreamReader  reader = null;
      OutputStreamWriter writer = null;

      try {
         reader = new InputStreamReader(
                  new FileInputStream(INPUT_FILENAME),
                  "UTF-8");
         writer = new OutputStreamWriter(
                  new FileOutputStream(OUTPUT_FILENAME, false),
                  StandardCharsets.UTF_8);
         int c;
         while ((c = reader.read()) != -1) {
            writer.write(c);
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

   private static void newStyleTry() {
      try (
            InputStreamReader reader = 
                  new InputStreamReader(
                  new FileInputStream(INPUT_FILENAME),
                  "UTF-8");
            OutputStreamWriter writer =
                  new OutputStreamWriter(
                  new FileOutputStream(OUTPUT_FILENAME, true),
                  StandardCharsets.UTF_8);
         )
      {
         int c;
         while ((c = reader.read()) != -1) {
            writer.write(c);
         }
      } catch (FileNotFoundException e) {
         System.err.println("A file for CopyCharacters not found.");
      } catch (IOException e) {
         System.err.println("Failed to copy characters.");
      }
   }

   public static void main(String[] args) {
      oldStyleTry();
      newStyleTry();
   }

}
