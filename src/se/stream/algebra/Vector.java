package se.stream.algebra;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

public class Vector<T> {

    private List<T> vector;
    private Optional<Vector<T>> optionalNext;
    private BinaryOperator<T> op;

    private Vector(List<T> vector, Vector<T> next, BinaryOperator<T> op) {
        this.vector = vector;
        this.optionalNext = Optional.of(next);
        this.op = op;
    }

    private Vector(List<T> vector) {
        this.vector = vector;
        this.optionalNext = Optional.empty();
        this.op = null;
    }

    private Stream<T> product() {
        return optionalNext.
            map(next -> next.product()
                .flatMap(x -> vector.stream()
                    .map(y -> op.apply(x, y))
                )
            )
            .orElse(vector.stream());
    }

    public static void main(String[] args) {
        List<Integer> lc = new ArrayList<>(Arrays.asList(1,2,3));
        List<Integer> lb = new ArrayList<>(Arrays.asList(1,2));
        List<Integer> la = new ArrayList<>(Arrays.asList(1,2));
        BinaryOperator<Integer> op = (x, y) -> x * y;

        Vector<Integer> va = new Vector<>(la);
        Vector<Integer> vb = new Vector<>(lb, va, op);
        Vector<Integer> vc = new Vector<>(lc, vb, op);

        vc.product()
            .forEach(e -> System.out.println(e));
    }
}