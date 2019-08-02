package se.collection.ordering.step3;

public interface Account extends Comparable<Account> {
   int getId();
   long getBalance();
   int compareById(Account a);
   int compareByBalance(Account a);
}
