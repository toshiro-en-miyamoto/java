package se.collection.ordering.step1;

class AbstractAccount implements Account {

   private final int id;
   public int getId() { return id; }

   private final long balance;
   public long getBalance() { return balance; }

   private static final int MIN_ID =  10_000;
   private static final int MAX_ID = 100_000;
   protected AbstractAccount() {
      this((int)(Math.random() * (MAX_ID - MIN_ID) + MIN_ID));
   }

   private static final int MIN_BAL =  1_000_000;
   private static final int MAX_BAL = 10_000_000;
   protected AbstractAccount(int id) {
      this.id = id;
      balance = (long)(Math.random() * (MAX_BAL - MIN_BAL) + MIN_BAL);
   }

   @Override public int compareById(Account a) {
      return Integer.compare(this.id, a.getId());
   }

   @Override public int compareByBalance(Account a) {
      return Long.compare(this.balance, a.getBalance());
   }

   @Override
   public String toString() {
      return new StringBuilder()
            .append(id)
            .append(':')
            .append(balance)
            .toString();
   }

}
