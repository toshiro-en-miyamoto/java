package se8exam.g13;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

class Main1136 {
   @SuppressWarnings("rawtypes")
   public static void main(String[] args) {
      try {
         ExecutorService service = Executors.newFixedThreadPool(2);
         Future f1 = service.submit(new Caller(1));
         Future f2 = service.submit(new Runner(2)); // line n1
         System.out.println(f1.get() + ":" + f2.get()); // line n2
      } catch (InterruptedException | ExecutionException e) {
         e.printStackTrace();
      }
   }
}
