package se8exam.g06;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Gold1116 {
   public static void main(String[] args) throws IOException {
      Properties prop = new Properties();
      prop.load(new FileInputStream("resources/app.properties")); // line n1
      System.out.println(prop.getProperty("server1"));
      System.out.println(prop.getProperty("server2", "192.168.0.6")); // line n2
      System.out.println(prop.getProperty("server3")); // line n3
   }
}
