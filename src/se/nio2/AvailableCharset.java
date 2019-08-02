package se.nio2;

import java.nio.charset.Charset;

final class AvailableCharset {

   public static void main(String[] args) {
      Charset.availableCharsets().values()
         .forEach((cs) ->
            System.out.printf("%s,%s,%s%n",
                  cs.name(),
                  cs.isRegistered(),
                  cs.aliases()));
   }

}
