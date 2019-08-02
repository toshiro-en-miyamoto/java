package se.functional.algorithm.fractional.v1.r1;

public class Fractional {
    public static void main(String[] args) {
        System.out.printf("= %d%n", Fractional.of(4));
    }

    private static Integer of(Integer value) {
        System.out.printf("%d ", value);
        if(value > 0)
            return value * Fractional.of(value - 1);
        else
            return 1;
    }
}
