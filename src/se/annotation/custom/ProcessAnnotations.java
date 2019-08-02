package se.annotation.custom;

import java.lang.reflect.Method;

public final class ProcessAnnotations {

   private static void processPreliminary(Class<?> c) {
      Preliminary preliminary
         = c.getAnnotation(se.annotation.custom.Preliminary.class);
      if (preliminary != null) {
         System.out.println("   @Preliminary");
      }
   }

   private static void processCopyright(Class<?> c) {
      Copyright copyright
         = c.getAnnotation(se.annotation.custom.Copyright.class);
      if (copyright != null) {
         System.out.println("   @Copyright");
         System.out.printf("      value=%s%n", copyright.value());
      }
   }

   private static void processRequestForEnhancement(Method m) {
      RequestForEnhancement rfe
         = m.getAnnotation(se.annotation.custom.RequestForEnhancement.class);
      if (rfe != null) {
         System.out.printf("Method: %s%n", m);
         System.out.println("   @RequestForEnhancement");
         System.out.printf("      id=%d%n", rfe.id());
         System.out.printf("      synopsis=%s%n", rfe.synopsis());
         System.out.printf("      engineer=%s%n", rfe.engineer());
         System.out.printf("      date=%s%n", rfe.date());
      }
   }

   public static void main(String[] args) throws ClassNotFoundException {
      Class<?> c = Class.forName("se.annotation.custom.TimeTravel");
      System.out.printf("Class: %s%n", c.getCanonicalName());

      processPreliminary(c);
      processCopyright(c);

      Method[] methods = c.getDeclaredMethods();
      for (Method m : methods) {
         processRequestForEnhancement(m);
      }
   }

}
