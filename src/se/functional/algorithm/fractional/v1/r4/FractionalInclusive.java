package se.functional.algorithm.fractional.v1.r4;

public class FractionalInclusive {
    private final static Integer BOUND_INCLUSIVE = 0;
    private final static Integer TERMINAL_VALUE  = 1;
    private static Integer VALUE_INTERVAL  = -1;

    public static void main(String[] args) {
        System.out.printf("= %d%n", FractionalInclusive.of(4));

        VALUE_INTERVAL = -2;
        System.out.printf("= %d%n", FractionalInclusive.of(5));

        VALUE_INTERVAL = -3;
        System.out.printf("= %d%n", FractionalInclusive.of(5));
    }

    private static Integer of(Integer value) {
        System.out.printf("%d ", value);
        if(hasNext(value))
            return apply(value, FractionalInclusive.of(next(value)));
        else
            return TERMINAL_VALUE;
    }

    /**
     * Returns a negative integer, 0, or a positive integer depending on whether
     * the first argument is less than, equal to, or greater than the second. 
     */
    private static int compare(Integer a, Integer b) {
        return VALUE_INTERVAL * (a - b);
    }

    private static boolean hasNext(Integer value) {
        return compare(value, BOUND_INCLUSIVE) < 0;
    }

    private static Integer next(Integer value) {
        Integer nextValue = value + VALUE_INTERVAL;
        if(compare(nextValue, BOUND_INCLUSIVE) > 0)
            return BOUND_INCLUSIVE;
        else
            return nextValue;
    }

    private static Integer apply(Integer a, Integer b) {
        return a * b;
    }
}
