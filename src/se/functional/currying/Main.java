package se.functional.currying;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static Function<String, Function<String, Function<String, String>>>
        format = a -> b -> s -> a + b + s;

    private static Stream<String> concat(Stream<String> s, String a, String b) {
        return s.map(format.apply(a).apply(b));
    }

    private static Function<Integer, Function<Integer, Function<Integer, Integer>>>
        fomula = a -> b -> x -> x * a + b;

    private static Stream<Integer> calc(Stream<Integer> x, Integer a, Integer b) {
        // F3.fomula is also available
        return x.map(fomula.apply(a).apply(b));
    }

    public static void main(String[] args) {
        System.out.println(
            concat(Arrays.asList("v","w","x").stream(),"a", "b")
                .collect(Collectors.toList())
        );

        System.out.println(
            calc(Arrays.asList(1,2,3,4,5).stream(), 3, 2)
                .collect(Collectors.toList())
        );
    }
}
