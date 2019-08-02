package se.concurrent.lock;

final class Safelock {

   public static void main(String[] args) {
      final SafelockFriend alphonse = new SafelockFriend("Alphonse");
      final SafelockFriend gaston   = new SafelockFriend("Gaston");
      new Thread(new BowLoop(alphonse, gaston)).start();
      new Thread(new BowLoop(gaston, alphonse)).start();
   }

}
