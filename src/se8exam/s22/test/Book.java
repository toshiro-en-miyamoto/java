package se8exam.s22.test;

public class Book {
   private String isbn;
   public void setIsbn(String isbn) {
      this.isbn = isbn;
   }
   protected void printInfo() {
      System.out.println(isbn);
   }
}
