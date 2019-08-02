package se8exam.g16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

final class Main1146 {
   private static final String URL = "";
   public static void main(String[] args) {
      String query = "SELECT id FROM author";
      try (Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement()) {

         ResultSet rs = stmt.executeQuery(query);
         stmt.executeQuery("SELECT id FROM book");
         while (rs.next()) {
            System.out.println("Publisher[ID]: " + rs.getInt("id"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
