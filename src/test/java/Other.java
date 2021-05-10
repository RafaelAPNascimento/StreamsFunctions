import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

}
