package se8exam.g13;

import java.util.concurrent.Callable;

public class Caller implements Callable<Integer> {
   private Integer id;

   public Caller(Integer id) {
      this.id = id;
   }

   public Integer call() throws Exception {
      return id;
   }
}
