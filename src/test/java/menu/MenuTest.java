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

    @Test
    @DisplayName("Show how many types of dishes there are in menu")
    public void shouldCalculateAllDistinctTypes() {

        long types =
            menu.stream()
                .map(Dish::getType)
                .distinct()
                .count();

        assertEquals(3, types);
    }

    @Test
    @DisplayName("show 3 most caloric dishes")
    public void shouldPresent3MostCaloricDishes() {

        int kcals = 950 + 700 + 800;

        List<Dish> most3calorics =
            menu.stream()
                .sorted(Comparator.comparing(Dish::getCalories).reversed())
                .limit(3)
                .collect(Collectors.toList());

        int sum =
                most3calorics.stream()
                    .mapToInt(Dish::getCalories)
                    .sum();

        assertEquals(sum, kcals);
    }

    @Test
    @DisplayName("does the menu have a veg option")
    public void shouldReturnTrueForVegOption() {

        boolean hasVeg =
                menu.stream()
                    .anyMatch(Dish::isVegetarian);

        Assertions.assertTrue(hasVeg);
    }

    @Test
    @DisplayName("find out whether the menu is healthy (that is, all dishes are below 1000 calories)")
    public void shouldBeHealthyMenu() {

        boolean healthy =
                menu.stream()
                    .allMatch(d -> d.getCalories() < 1000);

        Assertions.assertTrue(healthy);
    }

    @Test
    @DisplayName("How would you count the number of dishes in a stream using the map and reduce methods?")
    public void shouldReturnTheNumberOfDishes() {

        int expected = menu.size();
        int actual =
                menu.stream()
                    .reduce(0, (n, d) -> n + 1, Integer::sum);

        Assertions.assertEquals(expected, actual);
    }

}
