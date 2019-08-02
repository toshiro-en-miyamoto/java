package se8exam.s18;

public class Person {
   private String name;
   public Person(String name) {
      this.name = name;
   }
   public String getName() { return name; }
   public static void main(String[] args) {
      Person a = new Person("taro");
      Person b = new Person("taro");
      System.out.println(a.equals(b));
   }
}
