package se.stream.collect;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.IntSupplier;

public class PairCollector {

   static final class Pair implements Comparable<Pair> {
      private final int index;
      private final String value;

      private Pair(int index, String value) {
         this.index = index;
         this.value = new String(value);
      }

      public static Pair of(int index, String value) { return new Pair(index, value); }

      @Override public String toString() { return index + ":" + value; }
      @Override public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null) return false;
         if (this.getClass() != o.getClass()) return false;
         return index == ((Pair) o).index;
      }
      @Override public int hashCode() { return Integer.hashCode(index); }
      @Override public int compareTo(Pair o) { return Integer.compare(index, o.index); }
   }

   public static void main(String[] args) {

      String[] strings = new String[] { "A", "B", "C" };

      IntSupplier gen = new IntSupplier() {
         int i = 0;
         @Override public int getAsInt() { return i++; }
      };

      Arrays.stream(strings)
         .map(s -> Pair.of(gen.getAsInt(), s))
         .collect(
            () -> new ArrayList<Pair>(),
            (list, pair) -> list.add(pair),
            (list, other) -> list.addAll(other)
         )
         .stream()
         .forEach(o -> System.out.println(o));
   }

}