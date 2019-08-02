package se.nio2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

final class CopyBytes {
   private static final String INPUT_FILENAME  = "data/se.tutorials/basicio/xanadu.txt";
   private static final String OUTPUT_FILENAME = "data/se.tutorials/basicio/xanadu-copied.txt";

   private static void oldStyleTry() {
      FileInputStream  in  = null;
      FileOutputStream out = null;

      try {
         in  = new FileInputStream(INPUT_FILENAME);
         out = new FileOutputStream(OUTPUT_FILENAME, false);
         int b;
         while ((b = in.read()) != -1) {
            out.write(b);
         }
      } catch (FileNotFoundException e) {
         System.err.println("A file for CopyBytes not found.");
      } catch (IOException e) {
         System.err.println("Failed to copy bytes.");
      } finally {
         try {
            if (in  != null) { in.close(); }
            if (out != null) { out.close(); }
         } catch (IOException e) {
            System.err.println("Failed to close an output stream.");
         }
      }
   }

   private static void newStyleTry() {
      try (
            FileInputStream  in  = new FileInputStream(INPUT_FILENAME);
            FileOutputStream out = new FileOutputStream(OUTPUT_FILENAME, true);
         )
      {
         int b;
         while ((b = in.read()) != -1) {
            out.write(b);
         }
      } catch (FileNotFoundException e) {
         System.err.println("A file for CopyBytes not found.");
      } catch (IOException e) {
         System.err.println("Failed to copy bytes.");
      }
   }

   public static void main(String[] args) {
      oldStyleTry();
      newStyleTry();
   }

}
