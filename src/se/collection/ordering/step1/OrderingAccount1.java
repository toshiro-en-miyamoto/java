package se.collection.ordering.step1;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class OrderingAccount1 extends AbstractAccount {

   OrderingAccount1()      { super(); }
   OrderingAccount1(int i) { super(i); }

   public static void main(String... args) {

      final int SAMPLES = 4;
      Set<Account> set = new HashSet<>();

      for (int i = 0; i < SAMPLES; i++) {
         set.add(new OrderingAccount1());
      }

      // Collecting account IDs.
      int[] arrayId = new int[set.size()];
      int index = 0;
      Iterator<Account> it = set.iterator();
      while (it.hasNext()) {
         arrayId[index] = it.next().getId();
         index++;
      }

      // Inserting duplicate accounts
      for (int id : arrayId) {
         set.add(new OrderingAccount1(id));
      }

      System.out.println("HashSet - you can find it unordered.");
      System.out.println(" ID   Balance");
      System.out.println("----- -------");
      set.stream()
         .forEach(System.out::println);
      System.out.println();

      System.out.println("Sorted by ID - you can find duplate Accounts with default equals().");
      System.out.println(" ID   Balance");
      System.out.println("----- -------");
      set.stream()
         .sorted((a1, a2) -> Integer.compare(a1.getId(), a2.getId()))
         .forEach(System.out::println);

   }

}
