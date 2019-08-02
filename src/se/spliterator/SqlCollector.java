package se.spliterator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class SqlCollector<T> {

   @FunctionalInterface
   interface Function<T> {
      T apply(ResultSet rs) throws SQLException;
   }

   public static <T>
   List<T> executeStatement(Connection conn, String sql, SqlCollector.Function<T> mapper) throws SQLException {
      List<T> result = null;
      try (
         Statement stmt = conn.createStatement();
      ) {
         ResultSet rs = stmt.executeQuery(sql);
         result = collect(rs, mapper);
      }
      return result;
   }

   private static <T>
   List<T> collect(ResultSet rs, SqlCollector.Function<T> mapper) {
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
            false)
         .collect(Collectors.toCollection(ArrayList::new));
   }
}
