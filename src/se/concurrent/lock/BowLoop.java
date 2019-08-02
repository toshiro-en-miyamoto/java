package se.concurrent.lock;

import java.util.Random;

final class BowLoop implements Runnable {
   private SafelockFriend bower;
   private SafelockFriend bowee;
   BowLoop(SafelockFriend bower, SafelockFriend bowee) {
      this.bower = bower;
      this.bowee = bowee;
   }
   public void run() {
      Random random = new Random();
      while(true) {
         try {
            Thread.sleep(random.nextInt(10));
         } catch (InterruptedException e) {}
         bowee.bow(bower);
      }
   }
}
