package se8exam.g16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

final class Main0910 {
   private static final String URL = "";
   public static void main(String[] args) {
      try (Connection conn = DriverManager.getConnection(URL);
           Statement stmt = conn.createStatement();
           ResultSet rs1 = stmt.executeQuery("SELECT * FROM employee");
           ResultSet rs2 = stmt.executeQuery("SELECT * FROM customer")) { // line n1

         if (rs1.next() && rs2.next()) { // line n2
            System.out.println(rs1.getString("name") + ":" + rs2.getString("name"));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
}
