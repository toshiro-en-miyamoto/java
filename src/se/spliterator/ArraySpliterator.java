package se.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

class ArraySpliterator implements Spliterator<String> {
   private final String[] array;
   private int index;

   ArraySpliterator(String[] array) {
      this.array = array;
      index = 0;
   }

   @Override
   public boolean tryAdvance(Consumer<? super String> action) {
      action.accept(array[index++]);
      return index < array.length;
   }

   @Override
   public Spliterator<String> trySplit() {
      return null;
   }

   @Override
   public long estimateSize() {
      return array.length - index;
   }

   @Override
   public int characteristics() {
      return IMMUTABLE | SIZED;
   }
}
