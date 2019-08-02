package se.spliterator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ResultSetStream {

   @FunctionalInterface
   interface Function<T> {
      T apply(ResultSet rs) throws SQLException;
   }

   public static <T> Stream<T> get(
      ResultSet rs,
      ResultSetStream.Function<T> mapper)
         throws SQLException
   {
      return StreamSupport
         .stream(
            new Spliterator<T>() {
               @Override
               public boolean tryAdvance(Consumer<? super T> action) {
                  boolean next = false;
                  try {
                     if (rs.next()) {
                        action.accept(mapper.apply(rs));
                        next = true;
                     }
                  } catch (SQLException e) {
                     next = false;
                  }
                  return next;
               }

               @Override public Spliterator<T> trySplit() { return null; }
               @Override public long estimateSize() { return Long.MAX_VALUE; }
               @Override public int characteristics() { return IMMUTABLE; }
            },
            false);
   }
}
