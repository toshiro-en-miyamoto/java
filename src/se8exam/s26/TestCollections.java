package se8exam.s26;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
final class TestCollections {
   public static void main(String[] arg) {
      add(empty(), "Empty");
      add(singleton(), "Singleton");
      add(fixedSize(), "Fixed-size");
      add(unmodifiable(), "Unmodifiable");
   }
   private static void add(List<String> list, String type) {
      try {
         list.add("World");
         System.out.format("%s %s%n", type, list);
      } catch (UnsupportedOperationException e) {
         System.out.format("%s does not support add()%n", type);
      }
   }
   private static List<String> empty() {
      return Collections.emptyList();
   }
   private static List<String> singleton() {
      return Collections.singletonList("Hello");
   }
   private static List<String> fixedSize() {
      return Arrays.asList("Hello");
   }
   private static List<String> unmodifiable() {
      List<String> list = new ArrayList<>();
      list.add("Hello");
      return Collections.unmodifiableList(list);
   }
}