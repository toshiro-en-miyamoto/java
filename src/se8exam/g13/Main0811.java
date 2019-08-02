package se8exam.g13;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class Main0811 {
   public static void main(String[] args) {
      try {
         ExecutorService service = Executors.newSingleThreadExecutor();
         Future<String> future = service.submit(new MyCallable());
         System.out.println("Main:OK");
         System.out.println(future.get());
      } catch(InterruptedException | ExecutionException e) {
         e.printStackTrace();
      }
   }
}
