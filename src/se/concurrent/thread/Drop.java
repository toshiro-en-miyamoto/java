package se.concurrent.thread;

final class Drop {
   private String message;
   private boolean empty = true;

   public synchronized String take() {
      while (empty) {
         try {
            System.out.println("waiting in take()");
            wait();
         } catch (InterruptedException e) {}
      }
      empty = true;
      notifyAll();
      return message;
   }

   public synchronized void put(String message) {
      while (!empty) {
         try {
            System.out.println("waiting in put()");
            wait();
         } catch(InterruptedException e) {}
      }
      empty = false;
      this.message = message;
      notifyAll();
   }
}
