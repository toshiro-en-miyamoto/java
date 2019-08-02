package se8exam.s01;

class Item {
   String name;
   int price = 100;
}

public class Main {
   public static void main(String[] args) {
      Item[] items = new Item[3];
      int total = 0;
      for (int i = 0; i < items.length; i++) {
         total += items[i].price;
      }
      System.out.println(total);
   }
   public static void main2(String[] args) {
      String[] str = {new String(), new String()};
      int i = 0;
      for (String s : str) {
         System.out.println(s.concat("e" + i));
         i++;
      }
   }
   public static void main3(String[] args) {
      String[] str = new String[2];
      str[0] = new String();
      str[1] = new String();
      int i = 0;
      for (String s : str) {
         System.out.println(s.concat("e" + i));
         i++;
      }
   }
   public static void main4(String[] args) {
      String[] str = new String[2];
      int i = 0;
      for (String s : str) {
         s.concat("e" + i);
         i++;
      }
      for (i = 0; i < str.length; i++) {
         System.out.println(str[i]);
      }
   }
}
