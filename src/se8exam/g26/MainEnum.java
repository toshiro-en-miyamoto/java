package se8exam.g26;
public class MainEnum {
   static DayOfWeekEnum getToday() {
      // return 8; // compile error
      return DayOfWeekEnum.SUNDAY;
   }
   public static void main(String args[]) {
      DayOfWeekEnum today = getToday();
      assert today == DayOfWeekEnum.SUNDAY;
   }
}