package se8exam.g05;

import java.util.Map;
import java.util.TreeMap;

public class Main1115 {
   public static void main(String[] args) {
      Map<Integer, String> items = new TreeMap<>();
      items.put(726, "Stinger");
      items.put(333, "Axis");
      items.put(524, "Wall-master");
      items.put(901, "Stinger");
      System.out.println(items);
   }
}
