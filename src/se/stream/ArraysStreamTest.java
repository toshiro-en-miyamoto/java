package se.stream;

import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

class ArraysStreamTest {

   @Test
   void test() {
      Stream<String> ss = Stream.of("Hello", "Stream", "API");
      assert ss.collect(Collectors.joining(" ")).equals("Hello Stream API");
      DoubleStream ds = DoubleStream.of(1.0, 2.0, 3.0);
      assert ds.sum() == 6.0;
   }

}
