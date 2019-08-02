package se8exam.g04;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ListMusician {
   public static void main(String[] args) {
      List<Musician> musicList = Arrays.asList(
         new Musician("Jimi Hendrix", Musician.Category.ROCK),
         new Musician("Eric Dolphy", Musician.Category.JAZZ),
         new Musician("J.S.Bach", Musician.Category.CLASSICAL),
         new Musician("Charles Mingus", Musician.Category.JAZZ)
      );

      Map<Musician.Category, List<String>> map = musicList.stream()
         .collect(
            TreeMap::new,
            (treeMap, musician) -> {
               treeMap
                  .computeIfAbsent(
                     musician.getCategory(),
                     (k) -> new ArrayList<String>())
                  .add(musician.getName());
            },
            TreeMap::putAll);

      System.out.println(map);
   }
   public static void main6(String[] args) {
      List<Musician> musicList = Arrays.asList(
         new Musician("Jimi Hendrix", Musician.Category.ROCK),
         new Musician("Eric Dolphy", Musician.Category.JAZZ),
         new Musician("J.S.Bach", Musician.Category.CLASSICAL),
         new Musician("Charles Mingus", Musician.Category.JAZZ)
      );

      Collector<String, ?, List<String>> collector1 =
         Collectors.toList();

      Collector<Musician, ?, List<String>> collector2 =
         Collectors.mapping(Musician::getName, collector1);

      Collector<Musician, ?, Map<Musician.Category, List<String>>> collector3 =
         Collectors.groupingBy(Musician::getCategory, collector2);

      Map<Musician.Category, List<String>> map = musicList.stream()
         .collect(collector3);

      System.out.println(map);
   }
   public static void main5(String[] args) {
      List<Musician> musicList = Arrays.asList(
         new Musician("Jimi Hendrix", Musician.Category.ROCK),
         new Musician("Eric Dolphy", Musician.Category.JAZZ),
         new Musician("J.S.Bach", Musician.Category.CLASSICAL),
         new Musician("Charles Mingus", Musician.Category.JAZZ)
      );

      Collector<String, ?, List<String>> collector1 =
         Collectors.toList();

      Collector<Musician, ?, List<String>> collector2 =
         Collectors.mapping(Musician::getName, collector1);

      List<String> list = musicList.stream()
         .collect(collector2);

      System.out.println(list);
   }
   public static void main4(String[] args) {
      List<Musician> musicList = Arrays.asList(
         new Musician("Jimi Hendrix", Musician.Category.ROCK),
         new Musician("Eric Dolphy", Musician.Category.JAZZ),
         new Musician("J.S.Bach", Musician.Category.CLASSICAL),
         new Musician("Charles Mingus", Musician.Category.JAZZ)
      );

      Collector<String, ?, List<String>> collector1 =
         Collectors.toList();

      List<String> list = musicList.stream()
         .map(Musician::getName)
         .collect(collector1);

      System.out.println(list);
   }
   public static void main3(String[] args) {
      List<Musician> musicList = Arrays.asList(
         new Musician("Jimi Hendrix", Musician.Category.ROCK),
         new Musician("Eric Dolphy", Musician.Category.JAZZ),
         new Musician("J.S.Bach", Musician.Category.CLASSICAL),
         new Musician("Charles Mingus", Musician.Category.JAZZ)
      );

      Collector<String, ?, ArrayList<String>> collector =
         Collector.of(
            ArrayList::new,
            ArrayList::add,
            (left, right) -> { left.addAll(right); return left; });

      List<String> list = musicList.stream()
         .map(Musician::getName)
         .collect(collector);

      System.out.println(list);
   }
   public static void main2(String[] args) {
      List<Musician> musicList = Arrays.asList(
         new Musician("Jimi Hendrix", Musician.Category.ROCK),
         new Musician("Eric Dolphy", Musician.Category.JAZZ),
         new Musician("J.S.Bach", Musician.Category.CLASSICAL),
         new Musician("Charles Mingus", Musician.Category.JAZZ)
      );

      List<String> list = musicList.stream()
         .map(Musician::getName)
         .collect(
            ArrayList::new,
            ArrayList::add,
            ArrayList::addAll);

      System.out.println(list);
   }
   public static void main1(String[] args) {
      List<Musician> musicList = Arrays.asList(
         new Musician("Jimi Hendrix", Musician.Category.ROCK),
         new Musician("Eric Dolphy", Musician.Category.JAZZ),
         new Musician("J.S.Bach", Musician.Category.CLASSICAL),
         new Musician("Charles Mingus", Musician.Category.JAZZ)
      );

      Map<Musician.Category, List<String>> map = musicList.stream()
         .collect(Collectors.groupingBy(
            Musician::getCategory,
            Collectors.mapping(Musician::getName, Collectors.toList())));

      System.out.println(map);
   }
}
// {CLASSICAL=[J.S.Bach], JAZZ=[Eric Dolphy, Charles Mingus], ROCK=[Jimi Hendrix]}