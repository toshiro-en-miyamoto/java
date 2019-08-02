package se.collection.ordering.step1;

public interface Account {
   int getId();
   long getBalance();
   int compareById(Account a);
   int compareByBalance(Account a);
}
