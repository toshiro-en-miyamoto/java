package se8exam.g02;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {

   public static void main(String[] args) {
      Map<Integer, String> m1 = new HashMap<>();
      m1.put(2, "b");
      m1.put(3, "c");
      m1.put(1, "a");

      Map<Integer, String> m2 = new TreeMap<Integer, String>(
            new Comparator<Integer>() {
               public int compare(Integer i1, Integer i2) {
                  return i2.compareTo(i1);
               }
            });

      m2.putAll(m1);

      for (Map.Entry<Integer, String> entry : m2.entrySet()) {
         System.out.println(entry.getValue() + " ");
      }
   }
}
