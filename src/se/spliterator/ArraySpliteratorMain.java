package se.spliterator;

import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ArraySpliteratorMain {
   private static final String[] ARRAY = {
      "If", "this", "spliterator", "can", "be", "partitioned", ",",
      "returns", "a", "Spliterator", "covering", "elements", ",",
      "that", "will", ",", "upon", "return", "from", "this", "method", ",",
      "not", "be", "covered", "by", "this", "Spliterator", "."
   };

   public static void main(String[] args) {
      collect1();
      collect2();
   }

   private static void collect2() {
      List<String> list = StreamSupport
         .stream(
            new Spliterator<String>() {
               private final String[] array = ARRAY;
               private int index = 0;

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
            },
            false
         )
         .collect(Collectors.toList());

      list.forEach(System.out::println);
   }

   private static void collect1() {
      List<String> list = StreamSupport
         .stream(new ArraySpliterator(ARRAY), false)
         .collect(Collectors.toList());

      list.parallelStream().count();
      // list.forEach(System.out::println);
   }
}
