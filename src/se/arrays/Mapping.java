package se.arrays;

import java.util.Arrays;
import java.util.OptionalInt;

public class Mapping {
   static final int OTHER = 999;

   static final int[][] map1 = {
         // {result, src1}
         {10, 1},
         {20, 2},
         {30, 3}
         };

   static final int[][] map2 = {
         // {result, src1, src2}
         {100, 1, 10},
         {200, 2, 20},
         {300, 3, 30}
         };

   static int mapping1(int[][] map, int src1) {
      var result = OptionalInt.empty();
      for (int[] m : map) {
         if (src1 == m[1]) {
            result = OptionalInt.of(m[0]);
            break;
         }
      }
      return result.orElse(OTHER);
   }

   static int mapping1s(int[][] map, int src1) {
      return Arrays.stream(map)
            .filter(m -> m[1] == src1)
            .findFirst()
            .map(m -> m[0])
            .orElse(OTHER);
   }

   static int mapping2(int[][] map, int src1, int src2) {
      var result = OptionalInt.empty();
      for (int[] m : map) {
         if (src1 == m[1] && src2 == m[2]) {
            result = OptionalInt.of(m[0]);
            break;
         }
      }
      return result.orElse(OTHER);
   }

   static int mapping2s(int[][] map, int src1, int src2) {
      return Arrays.stream(map)
            .filter(m -> m[1] == src1 && m[2] == src2)
            .findFirst()
            .map(m -> m[0])
            .orElse(OTHER);
   }

   public static void main(String[] args) {
      assert mapping1(map1, 2) == 20;
      assert mapping1(map1, 9) == 999;
      assert mapping1s(map1, 2) == 20;
      assert mapping1s(map1, 9) == 999;

      assert mapping2(map2, 2, 20) == 200;
      assert mapping2(map2, 2, 99) == 999;
      assert mapping2s(map2, 2, 20) == 200;
      assert mapping2s(map2, 2, 99) == 999;
   }
}
