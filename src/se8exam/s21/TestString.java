package se8exam.s21;

class TestString {
   String val;
   public TestString(String val) {
      this.val = val;
   }
   @Override
   public String toString() {
      return val + " from " + getClass().getSimpleName();
   }
}
