package se8exam.g20;

import java.util.Locale;

final class Main1154 {
   public static void main(String[] args) {
      // Locale loc1 = Locale.get("JAPAN");
      // Locale loc2 = Locale.getInstance("ja");
      // Locale loc3 = Locale.getLocaleFactory("JP");
      Locale loc4 = Locale.JAPAN;
      Locale loc5 = new Locale("ja", "JP");
      assert loc4.equals(loc5);
      Locale loc6 = Locale.JAPANESE;
      assert loc5.equals(loc6);
   }
}
