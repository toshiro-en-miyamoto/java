package se8exam.g04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListMusician2 {
   static
   List<Musician> musicList = Arrays.asList(
         new Musician("Jimi Hendrix", Musician.Category.ROCK),
         new Musician("Eric Dolphy", Musician.Category.JAZZ),
         new Musician("J.S.Bach", Musician.Category.CLASSICAL),
         new Musician("Charles Mingus", Musician.Category.JAZZ)
      );

   public static void main(String[] args) {
      Map<Musician.Category, List<String>> result = musicList.stream()
         .collect(
            Collectors.groupingBy(
               Musician::getCategory,
               Collectors.mapping(
                  Musician::getName,
                  Collectors.toList())));

      System.out.println(result);
   }

   public static void main5(String[] args) {
      Map<Musician.Category, List<Musician>> result = musicList.stream()
         .collect(
            Collectors.groupingBy(
               Musician::getCategory,
               Collectors.toList()));

      System.out.println(result);
   }

   public static void main4(String[] args) {
      Map<Musician.Category, List<Musician>> result = musicList.stream()
         .collect(
            Collectors.groupingBy(Musician::getCategory));

      System.out.println(result);
   }

   public static void main3(String[] args) {
      List<String> result = musicList.stream()
         .map(Musician::getName)
         .collect(Collectors.toList());

      System.out.println(result);
   }

   public static void main3a(String[] args) {
      List<String> result = musicList.stream()
         .collect(
            Collectors.mapping(
               Musician::getName,
               Collectors.toList()));

      System.out.println(result);
   }

   public static void main2(String[] args) {
      List<Musician> result = musicList.stream()
         .collect(Collectors.toList());

      System.out.println(result);
   }

   public static void main1(String[] args) {
      List<Musician> result = musicList.stream()
         .collect(
            ArrayList::new,
            ArrayList::add,
            ArrayList::addAll);

      System.out.println(result);
   }

}
