package se8exam.g07;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Gold1117 {
   public static void main(String[] args) {
      String dirName = "/app";

      Path p1 = Paths.get(dirName, "module1", "msg1.bin");
      assert "/app/module1/msg1.bin".equals(p1.toString());

      Path p2 = p1.getParent();
      assert "/app/module1".equals(p2.toString());

      Path p3 = p2.resolveSibling("module2");
      assert "/app/module2".equals(p3.toString());

      Path p4 = p3.resolve("msg2.bin");
      assert "/app/module2/msg2.bin".equals(p4.toString());
   }
   public static void main1(String[] args) {
      Path p = Paths.get("/movies/sample/ex.mp4");
      System.out.println(p.getNameCount() + ":" + p.getName(1) + ":" + p.getFileName());
   }
   // 3:sample:ex.mp4
}
