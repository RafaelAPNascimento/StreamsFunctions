package exes01;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class Others {
    
    // get evens
    static void m01() {

        Supplier<HashSet<Integer>> supplier = HashSet::new;
        ObjIntConsumer<HashSet<Integer>> accBiConsumer = (h, i) -> h.add(i);
        BiConsumer<HashSet<Integer>, HashSet<Integer>> combBiConsumer = (hs1, hs2) -> hs1.addAll(hs2);

        Set<Integer> s =
            IntStream.of(1,2,3,4,5,6,7,8,9,0)
                .filter(n -> n % 2 == 1)
                //.collect(HashSet::new, HashSet::add, HashSet::addAll);
                .collect(supplier, accBiConsumer, combBiConsumer);
        
        s.stream().forEach(System.out::println);
    }

    // flattering: get unique chars from a list of words
    static void m02() {

        String[] words = {"hello", "word"};
        Arrays.stream(words)
                .map(s -> s.split(""))
                .flatMap(arr -> Arrays.stream(arr))
                .distinct()
                .forEach(System.out::println);
    }

    // 1. Given a list of numbers, how would you return a list of the square of each number? For
    //example, given [1, 2, 3, 4, 5] you should return [1, 4, 9, 16, 25].
    static void m03() {

        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
        ints.stream()
                .map(n -> Math.pow(n, 2))
                .forEach(System.out::println);
    }

    // 2. Given two lists of numbers, how would you return all pairs of numbers? For example, given a
    //list [1, 2, 3] and a list [3, 4] you should return [(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]. For
    //simplicity, you can represent a pair as an array with two elements.
    static void m04() {

        int[] l1 = {1, 2, 3};
        int[] l2 = {3, 4};

        List<int[]> r =
            Arrays.stream(l1)
                .mapToObj(n1 -> Arrays.stream(l2).mapToObj(n2 -> new int[]{n1, n2}))
                .flatMap(s -> s)
                .collect(toList());

        r.forEach(arr -> System.out.println(arr[0]+", "+arr[1]));

        /* author's
            numbers1.stream()
                .flatMap(i -> numbers2.stream()
                                        .map(j -> new int[]{i, j})
                )
                .collect(toList());
         */
    }

    // extend the previous example to return only pairs whose sum is divisible by 3
    static void m05() {

        int[] l1 = {1, 2, 3};
        int[] l2 = {3, 4};

        Arrays.stream(l1)
                .mapToObj(n1 -> Arrays.stream(l2).mapToObj(n2 -> new int[]{n1, n2}))
                .flatMap(n -> n)
                .filter(arr -> (arr[0] + arr[1]) % 3 == 0)
                .collect(toList());
    }


    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator;
    }

    public static void main(String[] args) {
        m04();
    }


    
}
