package se.regex.csv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;

class ExcelCSVParserTest {

   // The following row represents seven fields:
   //    assert field[0].equals("Ten Thousand");
   //    assert field[1].equals("10000");
   //    assert field[2].equals(" 2710 ");
   //    assert field[3].equals("");
   //    assert field[4].equals("10,000");
   //    assert field[5].equals("It's \"10 Grand\", baby");
   //    assert field[6].equals("10K");
   final static String INPUT = "Ten Thousand,10000, 2710 ,,\"10,000\",\"It's \"\"10 Grand\"\", baby\",10K";

   @Test
   void test() {
      CSVParser parser = new ExcelCSVParser();
      List<String> result = parser.parse(INPUT);
      assertEquals(result.get(0), "Ten Thousand");
      assertEquals(result.get(1), "10000");
      assertEquals(result.get(2), " 2710 ");
      assertEquals(result.get(3), "");
      assertEquals(result.get(4), "10,000");
      assertEquals(result.get(5), "It's \"10 Grand\", baby");
      assertEquals(result.get(6), "10K");
   }

}
