import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Other {

    @DisplayName("Given a list of words, return a list of the number of characters for each word")
    @Test
    public void shouldReturnNumberOfWords() {

        List<String> words = List.of("yellow", "green", "blue", "red");

        List<Integer> sizes =
            words.stream()
                .map(String::length)
                .collect(Collectors.toList());

        Iterator<String> it1 = words.iterator();
        Iterator<Integer> it2 = sizes.iterator();

        while (it1.hasNext() && it2.hasNext())
            Assertions.assertEquals(it1.next().length(), it2.next());
    }

    @DisplayName("Given a list of words, should a list of unique characters")
    @Test
    public void shouldReturnUniqueChars() {

        List<String> expected = List.of("H", "e", "l", "o", "W", "r", "d");

        List<String> actual =
            List.of("Hello", "World")
                .stream()
                .map(w -> w.split(""))
                .flatMap(Stream::of)
                .distinct()
                .collect(Collectors.toList());

        Assertions.assertEquals(expected.size(), actual.size());

        Iterator<String> it1 = expected.iterator();
        Iterator<String> it2 = actual.iterator();

        while (it1.hasNext() && it2.hasNext())
            Assertions.assertEquals(it1.next(), it2.next());
    }

    @DisplayName("Given a list of numbers, return the list of squares of each number")
    @Test
    public void shouldReturnSquareList() {

        List.of(1, 2, 3, 4, 5)
                .stream()
                .map(n -> n * n)
                .collect(Collectors.toList());
    }

    @DisplayName("Given 2 lists of numbers, return a list of pairs (List<Integer[]>)")
    @Test
    public void shouldReturnListOfPairs() {

        List<Integer> l1 = List.of(1, 2, 3);
        List<Integer> l2 = List.of(3, 4);

        List<int[]> r =
            l1.stream()
                .flatMap(n1 -> l2.stream().map(n2 -> new int[]{n1, n2}))
                .peek(arr -> System.out.println(Arrays.toString(arr)))
                .collect(Collectors.toList());
    }

}
