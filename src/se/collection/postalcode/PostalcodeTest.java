package se.collection.postalcode;

import java.util.Collections;

public final class PostalcodeTest {

   public static void main(String...args) {
      if (args.length == 0) {
         long count = Postalcodes.stream().count();
         System.out.printf("%,d codes in Japan%n", count);               
      } else {
         long count = Postalcodes.groupedByPrefecture()
               .getOrDefault(args[0], Collections.emptyList())
               .stream()
               .count();
         System.out.printf("%,d codes in %s%n", count, args[0]);
      }
   }

}
