package se8exam.g18;

public class User {
   private String name;
   private static int count;

   static {
      count = 0;
   }

   public User(String name) {
      this.name = name;
      ++count;
   }

   public static int getCount() {
      return count;
   }

   public static void main(String[] args) {
      User u1 = new User("Adam Ondra");
      User u2 = new User("Chris Sharm");
      User u3 = new User("Alex Megos");
      User u4 = new User("Ramon Julian");
      System.out.format("%s,%s,%s,%s%n", u1,u2,u3,u4);
      u4 = null;
      u3 = u2;
      System.out.println(User.getCount());
   }

   public String toString() {
      return this.name;
   }
}
