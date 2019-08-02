package se.concurrent.lock;

final class DeadlockFriend {
   private final String name;
   DeadlockFriend(String name) { this.name = name; }
   String getName() { return this.name; }
   synchronized void bow(DeadlockFriend bower) {
      System.out.format("%s: %s has bowed to me.%n", 
            this.name, bower.getName());
      bower.bowBack(this);
   }
   synchronized void bowBack(DeadlockFriend bower) {
      System.out.format("%s: %s has bowed back to me.%n",
            this.name, bower.getName());
   }
}
