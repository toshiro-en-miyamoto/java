package se.annotation.predefined;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class PredefinedAnnotation {

   @SuppressWarnings("deprecation")
   private static void callingDeprecatedMethod() {
      AnotherClass.deprecatedMethod();
   }

   @SuppressWarnings("rawtypes")
   static List getRawList() {
      return new ArrayList();
   }

   @SuppressWarnings("unchecked")
   static void usingRawList() {
      List<String> list = getRawList();
      list.addAll(Collections.<String>emptyList());
   }

   @SuppressWarnings({ "unchecked", "rawtypes" })
   static void processingRawList() {
      List list = Collections.<String>emptyList();
      list.addAll(Collections.<String>emptyList());
   }

   @SafeVarargs
   static void m(List<String>...lists) {
   }

   public static void main(String... args) {
      m(Collections.<String>emptyList());

      callingDeprecatedMethod();
      usingRawList();
      processingRawList();
   }

}
