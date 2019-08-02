package se.formatter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class FormatterTest {

   @Test
   void test1() {
      DecimalFormat formatter = new DecimalFormat("0000000000");
      long value = 123_45678L;
      assertEquals("0012345678",formatter.format(value));
   }

   @Test
   void test2() {
      DecimalFormat formatter = new DecimalFormat("0000"); // minimum 4 digits
      long value = 123_45678L;
      assertEquals("12345678",formatter.format(value));
   }

   @Test
   void test3() {
      DecimalFormatSymbols symbols = new DecimalFormatSymbols();
      symbols.setGroupingSeparator('-');
      DecimalFormat formatter = new DecimalFormat("00000,00000", symbols);
      // formatter.setGroupingSize(5);
      long value = 123_45678L;
      assertEquals("00123-45678",formatter.format(value));
   }

}
