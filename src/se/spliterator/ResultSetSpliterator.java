package se.spliterator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

public final class ResultSetSpliterator<T> implements Spliterator<T> {
   private final ResultSet rs;
   private final Function<ResultSet, T> mapper;

   public ResultSetSpliterator(ResultSet rs, Function<ResultSet, T> mapper) {
      this.rs = rs;
      this.mapper = mapper;
   }

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
}
