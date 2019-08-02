package se.collection.ordering.step2;

public interface Account {
   int getId();
   long getBalance();
   int compareById(Account a);
   int compareByBalance(Account a);
}
