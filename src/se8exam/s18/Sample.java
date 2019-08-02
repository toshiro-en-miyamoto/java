package se8exam.s18;

public class Sample {
   private int num;
   private String name;
   public Sample(int num, String name) {
      this.num = num;
      this.name = name;
   }
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }
      if (obj instanceof Sample) {
         Sample s = (Sample) obj;
         this.name = s.name;
         return s.num == this.num;
//         return s.num == this.num && s.name.equals(this.name);
      }
      return false;
   }
}
