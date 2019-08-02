package se.collection.map;

import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AlgebraVector
{
    public static void main(String[] args)
    {
        final LinkedHashMap<String, Double> a
            = new LinkedHashMap<String, Double>();
        a.put("A", 2.0d);

        final LinkedHashMap<Integer, Double> b
            = new LinkedHashMap<Integer, Double>();
        b.put(3, 4.0d);
        b.put(2, 5.0d);

        final LinkedHashMap<String, Double> c
            = new LinkedHashMap<String, Double>();
        c.put("X", 3.0d);

        System.out.println("==== Map a: ====");
        a.entrySet().stream().forEach(e -> System.out.println(e));
        System.out.println("==== Map b: ====");
        b.entrySet().stream().forEach(e -> System.out.println(e));
        System.out.println("==== Map c: ====");
        c.entrySet().stream().forEach(e -> System.out.println(e));

        Map<String, Double> abc = null;

        System.out.println("==== Test 1: ====");
        abc = outerProduct(
            // ab,
            outerProduct(a, b,
                (t,u) -> t + Integer.toString(u),
                (vt,vu) -> vt * vu,
                () -> new LinkedHashMap<String, Double>()
            ),
            c,
            (t,u) -> t + u,
            (vt,vu) -> vt * vu,
            () -> new LinkedHashMap<String, Double>()
        );
        abc.entrySet().stream().forEach(e -> System.out.println(e));

        System.out.println("==== Test 2: ====");
        abc = a.entrySet().stream()
            .flatMap(entry ->
                outerProduct(entry,
                    b,
                    (t,u) -> t + Integer.toString(u),
                    (vt,vu) -> vt * vu
                )
            )
            .flatMap(entry ->
                outerProduct(entry,
                    c,
                    (t,u) -> t + u,
                    (vt,vu) -> vt * vu
                )
            )
            .collect(Collectors
                .toMap(
                    er -> er.getKey(),
                    er -> er.getValue(),
                    (e1,e2) -> e2,
                    () -> new LinkedHashMap<String, Double>()
                )
            );
        abc.entrySet().stream().forEach(e -> System.out.println(e));

    }

    /**
     * Returns a Map<R,V> such that
     *   Map<R,V> r =
     *      [{f(t1,u1),g(vt1,vu1)}, {f(t1,u2),g(vt1,vu2)},
     *       {f(t2,u1),g(vt2,vu1)}, {f(t2,u2),g(vt2,vu2)},
     *       {f(t3,u1),g(vt3,vu1)}, {f(t3,u2),g(vt3,vu2)}];
     * provided two Maps as arguments
     *   Map<T,V> t =
     *      [{t1,vt1}, {t2,vt2}, {t3,vt3}];
     * and
     *   Map<U,V> u =
     *      [{u1,vu1}, {u2,vu2}];
     *
     * @param a a Map<T,V>
     * @param b another Map<U,V>
     * @param f the BiFunction<T,U,R> such that R r = f(T t, U u)
     * @param g the SimpleOperator<V> such that V v = g(V vt, V vu)
     * @return yet another Map<R,V>
     */
    public static <T,U,R,V> Map<R,V>
    outerProduct(
        Map<T,V> mapT,
        Map<U,V> mapU,
        BiFunction<T,U,R> f,
        BinaryOperator<V> g,
        Supplier<? extends Map<R,V>> s)
    {
        return mapT.entrySet().stream()
            .flatMap(et -> mapU.entrySet().stream()
                .map(eu -> new AbstractMap.SimpleEntry<R,V>(
                    f.apply(et.getKey(), eu.getKey()),
                    g.apply(et.getValue(), eu.getValue()))
                )
            )
            .collect(Collectors
                .toMap(
                    er -> er.getKey(),
                    er -> er.getValue(),
                    (a,b) -> b,
                    s
                )
            );
    }

    /**
     * Returns a Stream<Map.Entry<R,V>> such that
     *   Stream<Map.Entry<R,V>> :
     *      [{f(t1,u1),g(vt1,vu1)}, {f(t1,u2),g(vt1,vu2)}];
     * provided two arguments
     *   Map.Entry<T,V> = {t1,vt1};
     * and
     *   Map<U,V> = [{u1,vu1}, {u2,vu2}];
     *
     * @param e Map.Entry<T,V>
     * @param m Stream<Map.Entry<U,V>>
     * @param f BiFunction<T,U,R> such that R r = f(T t, U u)
     * @param g SimpleOperator<V> such that V v = g(V vt, V vu)
     * @return Stream<Map.Entry<R,V>>
     */
    public static <T,U,R,V> Stream<Map.Entry<R,V>>
    outerProduct(
        Map.Entry<T,V> e,
        Map<U,V> m,
        BiFunction<T,U,R> f,
        BinaryOperator<V> g)
    {
        return m.entrySet().stream()
            .map(eu -> new AbstractMap.SimpleEntry<R,V>(
                f.apply(e.getKey(),   eu.getKey()),
                g.apply(e.getValue(), eu.getValue()))
            );
    }
}