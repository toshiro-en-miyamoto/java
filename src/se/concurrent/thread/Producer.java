package se.concurrent.thread;

import java.util.Random;

final class Producer implements Runnable {
   private final Drop drop;

   public Producer(Drop drop) {
      this.drop = drop;
   }

   public void run() {
      String importantInfo[] = {
            "Mares eat oats",
            "Does eat oats",
            "Little lambs eat ivy",
            "A kid will eat ivy too"
       };
      Random random = new Random();
      for (int i = 0; i < importantInfo.length; i++) {
         drop.put(importantInfo[i]);
         try {
            Thread.sleep(random.nextInt(5000));
         } catch (InterruptedException e) {}
      }
      drop.put("Done");
   }
}
