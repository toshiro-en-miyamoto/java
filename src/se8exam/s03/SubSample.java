package se8exam.s03;

public class SubSample extends Sample {
   int price;
   public SubSample(int price) {
      super(null, 0); // added
      this.price = price;
   }
   public SubSample(String name, int num, int price) {
      super(name, num);
      this.price = price; // this(price);
   }
}
