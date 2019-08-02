package se.jdbc.rowset;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

final class JdbcRowSetSample {

   private static String url = "jdbc:derby://localhost:1527/JDBCTutorial";
   private static String command = "select * from Coffees";

   public static void main(String[] args) throws SQLException {
      /*
      Connection conn = DriverManager.getConnection(url);
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(command);
      */
      JdbcRowSet jrs = RowSetProvider.newFactory().createJdbcRowSet();
      jrs.setUrl(url);
      jrs.setCommand(command);
      jrs.execute();
      while(jrs.next()) {
         System.out.printf("%-24s %4d %3.2f %4d %4d%n",
            jrs.getString("cof_name"),
            jrs.getInt("sup_id"),
            jrs.getFloat("price"),
            jrs.getInt("sales"),
            jrs.getInt("Total"));
      }
   }

}
