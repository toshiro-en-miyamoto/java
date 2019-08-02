package se.functional.currying;

import java.util.function.Function;

public interface F3 extends Function<Integer, Function<Integer, Function<Integer, Integer>>>
{
    static F3 fomula = x -> y -> z -> x + y * z;
}
