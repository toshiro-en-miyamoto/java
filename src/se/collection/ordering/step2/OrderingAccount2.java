package se.collection.ordering.step2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class OrderingAccount2 extends AbstractAccount {

   OrderingAccount2()      { super(); }
   OrderingAccount2(int i) { super(i); }

   public static void main(String... args) {

      final int SAMPLES = 4;
      Set<Account> set = new HashSet<>();

      for (int i = 0; i < SAMPLES; i++) {
         set.add(new OrderingAccount2());
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
         set.add(new OrderingAccount2(id));
      }

      System.out.println("HashSet - you can find it unordered.");
      System.out.println(" ID   Balance");
      System.out.println("----- -------");
      set.stream()
         .forEach(System.out::println);
      System.out.println();

      System.out.println("Sorted by ID - you can NO LONGER find duplate Accounts with overridden equals().");
      System.out.println(" ID   Balance");
      System.out.println("----- -------");
      set.stream()
         .sorted((a1, a2) -> Integer.compare(a1.getId(), a2.getId()))
         .forEach(System.out::println);
      System.out.println();

      System.out.println("ArrayList copied from the HashSet - in the order they are returned by the HashSet's iterator.");
      List<Account> list = new ArrayList<>(set);
      System.out.println(" ID   Balance");
      System.out.println("----- -------");
      list.stream().forEach(System.out::println);
      System.out.println();

      System.out.println("ArrayList sorted by natural ordering - you can NOT sort Lists in their nutural ordering if they do not implement Comparable<E>.");
      // Collections.sort(list);  // compile-time error
      // list.stream().forEach(System.out::println);
      System.out.println();

      System.out.println("ArrayList sorted by Id with Comparator<E> - you can sort Lists with Comparator<E> even if they do not implement Comparable<E>.");
      list.sort(Account::compareById);
      System.out.println(" ID   Balance");
      System.out.println("----- -------");
      list.stream().forEach(System.out::println);

   }

}
