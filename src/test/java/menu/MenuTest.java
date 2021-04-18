package menu;

import org.junit.jupiter.api.*;
import study.domain.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MenuTest {

    static List<Dish> menu;

    @BeforeAll
    public static void init() {

        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH),
                new Dish("sushi", false, 500, Dish.Type.FISH),
                new Dish("cow", false, 600, Dish.Type.MEAT),
                new Dish("coconut", true, 950, Dish.Type.OTHER));
    }

    @Test
    @DisplayName("Should get 3 most caloric dishes")
    public void shouldGet3mostCaloricDishes() {

        final int TOTAL = 800 + 700 + 950;

        List<Dish> top3Cal =
                menu.stream()
                        .sorted((d1, d2) -> d2.getCalories() - d1.getCalories())
                        .sorted(Comparator.comparingInt(Dish::getCalories).reversed())
                        .limit(3)
                        //.collect(Collectors.toList());
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        int result =
                top3Cal.stream()
                    .mapToInt(Dish::getCalories)
                    .sum();

        assertEquals(TOTAL, result);
    }

    @Test
    @DisplayName("Present only vegs dishes")
    public void shouldPresentOnlyVegs() {

        boolean onlyVegs =
                menu.stream()
                    .filter(Dish::isVegetarian)
                    .allMatch(Dish::isVegetarian);

        assertTrue(onlyVegs);
    }
}
