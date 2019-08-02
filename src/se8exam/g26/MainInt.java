package se8exam.g26;
class MainInt {
   static int getToday() {
      // return DayOfWeekInt.SUNDAY;
      return 8;
   }
   public static void main(String args[]) {
      int today = getToday();
      // assert today == DayOfWeekInt.SUNDAY;
      assert today == 8;
   }
}