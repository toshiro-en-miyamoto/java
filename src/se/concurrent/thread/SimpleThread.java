package se.concurrent.thread;

final class SimpleThread {

   private static void threadMessage(String message) {
      System.out.format("%s: %s%n", Thread.currentThread().getName(), message);
   }

   public static void main(String[] args) throws InterruptedException {
      long patience = 7000;
      threadMessage("Starting the thread");
      long startTime = System.currentTimeMillis();

      Thread t = new Thread(() -> {
         String info[] = {
               "Mares eat oats",
               "Does eat oats",
               "Little lambs eat ivy",
               "A kid will eat ivy too"
          };
         try {
            for (int i = 0; i < info.length; i++) {
               Thread.sleep(4000);
               threadMessage(info[i]);
             }
         } catch (InterruptedException e) {
            threadMessage("I wasn't done!");
          }
       });

      t.start();
      while (t.isAlive()) {
         threadMessage("Strill waiting...");
         t.join(1000);
         if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
            threadMessage("Tired of waiting!");
            t.interrupt();
            t.join();;
         }
      }
      threadMessage("Finally!");
   }

}
