package se8exam.g13;

public class Runner implements Runnable {
   private Integer id;

   public Runner(Integer id) {
      this.id = id;
   }

   public void run() {
      System.out.println(id);
   }
}
