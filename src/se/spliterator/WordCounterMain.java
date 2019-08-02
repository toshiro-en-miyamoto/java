package se.spliterator;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class WordCounterMain {
   private static final String SENTENCE
    = " Nel   mezzo del cammin  di nostra  vita " +
      "mi  ritrovai in una  selva oscura" +
      " ché la  dritta via era   smarrita ";

   public static void main(String[] args) {
      assert 19 == countWordsIteratively(SENTENCE);

      Stream<Character> stream1 = IntStream
         .range(0, SENTENCE.length())
         .mapToObj(SENTENCE::charAt);
      assert 19 == countWords(stream1);

      Stream<Character> stream2 = IntStream
         .range(0, SENTENCE.length())
         .mapToObj(SENTENCE::charAt);
      assert 19 < countWords(stream2.parallel());

      Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
      Stream<Character> stream3 = StreamSupport.stream(spliterator, true);
      assert 19 == countWords(stream3);
   }

   private static int countWords(Stream<Character> stream) {
      WordCounter wordCounter = stream
         .reduce(new WordCounter(0, true),
                 WordCounter::accumulate,
                 WordCounter::combine);
      return wordCounter.getCounter();
   }

   private static int countWordsIteratively(String s) {
      int counter = 0;
      boolean lastSpace = true;
      //         " Nel  mezzo del "
      // isWhite  TFFFTTFFFFFTFFFT
      // counter 00111112222223333
      // lastSpc TTFFFTTFFFFFTFFFT
      for (char c : s.toCharArray()) {
         if (Character.isWhitespace(c))
            lastSpace = true;
         else {
            if (lastSpace) counter++;
            lastSpace = false;
         }
      }
      return counter;
   }
}
