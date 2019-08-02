package se.collection.postalcode;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class JapanPostCsvLoader implements Postalcodes.Loader {
   private static final Path CSVPATH;
   private static final String CHARSET;
   private static CSVParser PARSER;

   @Override
   public Set<Postalcode> load(Supplier<? extends Set<Postalcode>> factory) {

      Set<Postalcode> set = Collections.emptySet();

      try {
         set = Files.readAllLines(CSVPATH, Charset.forName(CHARSET))
            .stream()
            .map(PARSER::parse)
            .map(fields -> {
               return Postalcode.of(
                     fields.get(2).trim(),  // postal code
                     fields.get(6).trim(),  // prefecture
                     fields.get(7).trim(),  // city
                     fields.get(8).trim()    // area
               );
            })
            .collect(Collectors.toCollection(factory));
      } catch(IOException e) {
         e.printStackTrace();
      }

      return set;
   }

   static {
      CSVPATH = Paths.get(PostalcodeSettings.get("csv.filename"));
      CHARSET = PostalcodeSettings.get("csv.encoding");

      String parserName = PostalcodeSettings.get("csv.parser");
      try {
         Class<?> parserClass = Class.forName(parserName);
         Constructor<?> constructor = parserClass.getConstructor();
         PARSER = (CSVParser) constructor.newInstance();
      } catch (Exception e) {
         e.printStackTrace();
         PARSER = new CSVParser() {
            @Override public List<String> parse(String line) {
               return Collections.emptyList();
            }
         };
      }
   }
}
