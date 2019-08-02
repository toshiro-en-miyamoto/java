package se.annotation.custom;

import java.util.Date;

@Copyright("2002 Yoyodyne Propulsion Systems, Inc.")
@Preliminary
public class TimeTravel {

   @RequestForEnhancement(
         id = 2868724,
         synopsis = "Provide time-travel functionality",
         engineer = "Mr. Peabody",
         date = "4/1/2004"
      )
   public static void travelThroughTime(Date destination) {
   }

   @RequestForEnhancement(
         id = 4561414,
         synopsis = "Balance the federal budget"
      )
   public static void balanceFederalBudget() {
   }

}
