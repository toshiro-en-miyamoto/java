package se.functional.algorithm.fractional.v1.r4;

public class FractionalExclusive {
    private final static Integer BOUND_EXCLUSIVE = 0;
   //  private final static Integer TERMINAL_VALUE  = 1;
    private static Integer VALUE_INTERVAL  = -1;

    public static void main(String[] args) {
        System.out.printf("= %d%n", FractionalExclusive.of(4));

        VALUE_INTERVAL = -2;
        System.out.printf("= %d%n", FractionalExclusive.of(5));

        VALUE_INTERVAL = -3;
        System.out.printf("= %d%n", FractionalExclusive.of(5));
    }

    private static Integer of(Integer value) {
        System.out.printf("%d ", value);
        if(hasNext(value))
            return apply(value, FractionalExclusive.of(next(value)));
        else
            return value;
    }

    /**
     * Returns a negative integer, 0, or a positive integer depending on whether
     * the first argument is less than, equal to, or greater than the second. 
     */
    private static int compare(Integer a, Integer b) {
        return VALUE_INTERVAL * (a - b);
    }

    private static boolean hasNext(Integer value) {
        return compare(next(value), BOUND_EXCLUSIVE) < 0;
    }

    private static Integer next(Integer value) {
        return value + VALUE_INTERVAL;
    }

    private static Integer apply(Integer a, Integer b) {
        return a * b;
    }
}
