package se.spliterator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

class SqlCollectorTest {

   static class SqlPerson {
      private final int id;
      private final String name;

      SqlPerson(int id, String name) {
         this.id = id;
         this.name = name;
      }

      public String toString() {
         return String.format("%d:%s", id, name);
      }
   }

   private static void simple() {
      try (
         Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
      ) {

         List<SqlPerson> list = SqlCollector.executeStatement(
            conn,
            "select id, name from person",
            new SqlCollector.Function<SqlPerson>() {
               public SqlPerson apply(ResultSet rs) throws SQLException {
                  return new SqlPerson(
                     rs.getInt("id"),
                     rs.getString("name")
                     );
               }
            });

         list.stream()
            .forEach(System.out::println);

      } catch (SQLException e) {
         System.out.printf("%s (%s:%d)%n", e.getMessage(), e.getSQLState(), e.getErrorCode());
      }
   }

   private static void create() {
      try (
         Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
         Statement stmt = conn.createStatement();
      ) {
         stmt.executeUpdate("drop table if exists person");
         stmt.executeUpdate("create table person (id integer, name string)");
         stmt.executeUpdate("insert into person values(1, 'toshiro')");
         stmt.executeUpdate("insert into person values(2, 'yumi')");
      } catch (SQLException e) {
         System.out.printf("%s (%s:%d)%n", e.getMessage(), e.getSQLState(), e.getErrorCode());
      }

      assert Files.exists(Paths.get("").resolve("sample.db"));
   }

   public static void main(String[] args) {
      create();
      simple();
   }

}
