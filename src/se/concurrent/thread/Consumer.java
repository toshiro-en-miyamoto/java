package se.concurrent.thread;

import java.util.Random;

final class Consumer implements Runnable {
   private final Drop drop;

   Consumer(Drop drop) {
      this.drop = drop;
   }

   public void run() {
      Random random = new Random();
      for (String message = drop.take();
            !message.equals("Done");
            message = drop.take()) {
         System.out.format("RECV: %s%n", message);
         try {
            Thread.sleep(random.nextInt(5000));
         } catch (InterruptedException e) {}
      }
   }
}
