package se.concurrent.lock;

final class Deadlock {

   public static void main(String[] args) {
      final DeadlockFriend alphonse = new DeadlockFriend("Alphonse");
      final DeadlockFriend gaston   = new DeadlockFriend("Gaston");
      new Thread(() -> alphonse.bow(gaston)).start();
      new Thread(() -> gaston.bow(alphonse)).start();
   }

}
