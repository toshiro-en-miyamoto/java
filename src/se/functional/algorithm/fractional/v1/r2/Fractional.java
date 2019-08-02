package se.functional.algorithm.fractional.v1.r2;

public class Fractional {
    private static Integer VALUE_INTERVAL  = -1;

    public static void main(String[] args) {
        System.out.printf("= %d%n", Fractional.of(4));

        VALUE_INTERVAL = -2;
        System.out.printf("= %d%n", Fractional.of(5));

        VALUE_INTERVAL = -3;
        System.out.printf("= %d%n", Fractional.of(5));
    }

    private static Integer of(Integer value) {
        System.out.printf("%d ", value);
        if(value > 0)
            return value * Fractional.of(value + VALUE_INTERVAL);
        else
            return 1;
    }
}
