package se.jdbc.sqlite;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class HelloSQLite3 {

   // Download JDBC driver from: https://bitbucket.org/xerial/sqlite-jdbc/downloads/
   // The latest driver as of 2018-10-15 is sqlite-jdbc-3.23.1.jar (2018-05-29, 6.4MB)
   // With Eclipse, right click on a Java project, select Build Path → Add External Archives...
   // which will make the driver jar file appear in Referenced Libraries of the project.
   // Running this class will create the file sample.db in the working directory (user.dir).

   private static void hello() {
      try (
            Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement stmt = conn.createStatement();
            )
      {
         stmt.executeUpdate("drop table if exists person");
         stmt.executeUpdate("create table person (id integer, name string)");
         stmt.executeUpdate("insert into person values(1, 'toshiro')");
         stmt.executeUpdate("insert into person values(2, 'yumi')");

         ResultSet rs = stmt.executeQuery("select * from person");
         while(rs.next()) {
            System.out.printf("%d: %s%n", rs.getInt("id"),  rs.getString("name"));
         }
      } catch (SQLException e) {
         System.out.printf("%s (%s:%d)%n", e.getMessage(), e.getSQLState(), e.getErrorCode());
      }

      assert Files.exists(Paths.get("").resolve("sample.db"));
   }

   private static void types() {
      final String URL = "jdbc:sqlite:sample.db";
      try {
            Connection conn;
            DatabaseMetaData meta;
            Statement stmt;

            conn = DriverManager.getConnection(URL);
            meta = conn.getMetaData();
            assert meta.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            assert false == meta.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            assert false == meta.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
            assert meta.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            stmt = conn.createStatement();
            assert ResultSet.TYPE_FORWARD_ONLY == stmt.getResultSetType();
            assert ResultSet.CONCUR_READ_ONLY == stmt.getResultSetConcurrency();
            assert ResultSet.CLOSE_CURSORS_AT_COMMIT == stmt.getResultSetHoldability();
            stmt.close();

            stmt = conn.createStatement(
                  ResultSet.TYPE_FORWARD_ONLY,
                  ResultSet.CONCUR_READ_ONLY,
                  ResultSet.CLOSE_CURSORS_AT_COMMIT
                  );
            assert ResultSet.TYPE_FORWARD_ONLY == stmt.getResultSetType();
            assert ResultSet.CONCUR_READ_ONLY == stmt.getResultSetConcurrency();
            assert ResultSet.CLOSE_CURSORS_AT_COMMIT == stmt.getResultSetHoldability();
            stmt.close();

            try {
               stmt = conn.createStatement(
                     ResultSet.TYPE_FORWARD_ONLY,
                     ResultSet.CONCUR_READ_ONLY,
                     ResultSet.HOLD_CURSORS_OVER_COMMIT
                     );
               assert ResultSet.TYPE_FORWARD_ONLY == stmt.getResultSetType();
               assert ResultSet.CONCUR_READ_ONLY == stmt.getResultSetConcurrency();
               assert ResultSet.HOLD_CURSORS_OVER_COMMIT == stmt.getResultSetHoldability();
               stmt.close();
            } catch (SQLException e) {
               // SQLite only supports closing cursors at commit (null:0)
               System.out.printf("%s (%s:%d)%n", e.getMessage(), e.getSQLState(), e.getErrorCode());
            }

            try {
               stmt = conn.createStatement(
                     ResultSet.TYPE_FORWARD_ONLY,
                     ResultSet.CONCUR_UPDATABLE,
                     ResultSet.CLOSE_CURSORS_AT_COMMIT
                     );
               assert ResultSet.TYPE_FORWARD_ONLY == stmt.getResultSetType();
               assert ResultSet.CONCUR_UPDATABLE == stmt.getResultSetConcurrency();
               assert ResultSet.CLOSE_CURSORS_AT_COMMIT == stmt.getResultSetHoldability();
               stmt.close();
            } catch (SQLException e) {
               // SQLite only supports CONCUR_READ_ONLY cursors (null:0)
               System.out.printf("%s (%s:%d)%n", e.getMessage(), e.getSQLState(), e.getErrorCode());
            }

            try {
               stmt = conn.createStatement(
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY,
                     ResultSet.CLOSE_CURSORS_AT_COMMIT
                     );
               assert ResultSet.TYPE_SCROLL_INSENSITIVE == stmt.getResultSetType();
               assert ResultSet.CONCUR_READ_ONLY == stmt.getResultSetConcurrency();
               assert ResultSet.CLOSE_CURSORS_AT_COMMIT == stmt.getResultSetHoldability();
               stmt.close();
            } catch (SQLException e) {
               // SQLite only supports TYPE_FORWARD_ONLY cursors (null:0)
               System.out.printf("%s (%s:%d)%n", e.getMessage(), e.getSQLState(), e.getErrorCode());
            }

            conn.close();
         } catch (SQLException e) {
            System.out.printf("%s (%s:%d)%n", e.getMessage(), e.getSQLState(), e.getErrorCode());
         }
      
   }

   public static void main(String[] args) {
      hello();
      types();
   }

}
