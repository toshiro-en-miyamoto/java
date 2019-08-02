package se.collection.postalcode;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Postalcodes {
   private static Set<Postalcode> set = Collections.emptySet();

   interface Loader {
      Set<Postalcode> load(Supplier<? extends Set<Postalcode>> factory);
   }

   public static Stream<Postalcode> stream() {
      return set.stream();
   }

   public static Map<String, List<Postalcode>> groupedByPrefecture() {
      return set.stream()
            .collect(Collectors.groupingBy(Postalcode::getPref));
   }

   static {
      String loaderName = PostalcodeSettings.get("postalcode.loader");
      long t1 = System.currentTimeMillis();
      try {
         Class<?> loaderClass = Class.forName(loaderName);
         Constructor<?> constructor = loaderClass.getConstructor();
         Postalcodes.Loader loader = (Postalcodes.Loader) constructor.newInstance();
         set = loader.load(HashSet<Postalcode>::new);
      } catch (Exception e) {
         e.printStackTrace();
      }
      long t2 = System.currentTimeMillis();
      System.out.printf("%s has loaded %,d codes in %,d msec%n", loaderName, set.size(), t2 - t1);
      set.stream()
            .limit(4)
            .forEach(System.out::println);
   }

}
