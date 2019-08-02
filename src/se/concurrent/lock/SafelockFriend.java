package se.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class SafelockFriend {
   private final String name;
   private final Lock lock = new ReentrantLock();

   SafelockFriend(String name) { this.name = name; }
   String getName() { return this.name; }

   boolean impendingBow(SafelockFriend bower) {
      Boolean myLock = false;
      Boolean yourLock = false;
      try {
         myLock = lock.tryLock();
         yourLock = bower.lock.tryLock();
      } finally {
         if (! (myLock && yourLock)) {
            if (myLock) {
               lock.unlock();
            }
            if (yourLock) {
               bower.lock.unlock();
            }
         }
      }
      return myLock && yourLock;
   }

   void bow(SafelockFriend bower) {
      if (impendingBow(bower)) {
         try {
            System.out.format("%s: %s has bowed to me.%n", 
                  this.name, bower.getName());
            bower.bowBack(this);
         } finally {
            lock.unlock();
            bower.lock.unlock();
         }
      } else {
         System.out.format("%s: %s started to bow%n",
               this.name, bower.getName());
      }
   }

   void bowBack(SafelockFriend bower) {
      System.out.format("%s: %s has bowed back to me.%n",
            this.name, bower.getName());
   }
}
