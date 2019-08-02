package se.nio2;

import java.nio.file.Path;
import java.nio.file.Paths;

final class IntroPath {

   public static void main(String[] args) {

      Path p1, p2;

      String dirApp1Root = System.getenv("APP1_ROOT");
      assert "/usr/app1/bin".equals(dirApp1Root);
      p1 = Paths.get(dirApp1Root);
      p2 = p1.resolveSibling("config").resolve("app1.properties");
      assert "/usr/app1/config/app1.properties".equals(p2.toString());

      String currentDir = System.getProperty("user.dir");
      p1 = Paths.get(currentDir);
      p2 = Paths.get("").toAbsolutePath();  // current path
      assert p1.equals(p2);
   }

}
