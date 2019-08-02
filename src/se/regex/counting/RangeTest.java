package se.regex.counting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class RangeTest {

   @Test
   void test() {
      final String TEST_STRING = Long.valueOf(1234_5678_9012L).toString();
      assertEquals(TEST_STRING, "123456789012");

      final Pattern FOUR_BY_THREE = Pattern.compile("^([0-9]{4})([0-9]{4})([0-9]{4})$");
      Matcher matcher = FOUR_BY_THREE.matcher(TEST_STRING);

      if(matcher.find()) {
         assertEquals("1234", matcher.group(1));
         assertEquals("5678", matcher.group(2));
         assertEquals("9012", matcher.group(3));
      } else {
         fail("not found");
      }
   }

}
