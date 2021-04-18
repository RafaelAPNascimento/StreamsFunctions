package exes01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;
import study.domain.Dish;

public class Menu {

    static List<Dish> menu = Arrays.asList(
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
    
    // get 3 most calories dishes in menu as List
    static void m01() {        
        List<Dish> r = menu.stream().sorted((d1, d2) -> d2.getCalories() - d1.getCalories())
                            .limit(3)
                            .collect(toList());
        
        System.out.println(r);
    }
    
    // get all dishes above 350 kals, as array
    static void m02() {        
        Dish[] dishes = menu.stream()
                            .filter(d -> d.getCalories() > 350)                            
                            .toArray(Dish[]::new );
      //                      .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        
        Arrays.stream(dishes).forEach(System.out::println);
    }
    
    // get all veggies dish
    static void m03() {
        
        List<Dish> vegs =
            menu.stream()
                .filter(Dish::isVegetarian)
                //.collect(Collectors.toList());
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        
        
        vegs.stream().forEach(System.out::println);
    }
    
    // get how many types of dishes there are in menu
    static void m04() {
        
        long n =
            menu.stream()
                .map(Dish::getType)
                .distinct()
                .count();
        
        System.out.println(n);
    }

    // get 3 most caloric dishes
    static void m05() {

        menu.stream()
                .sorted((d2, d1) -> d1.getCalories() - d2.getCalories())
                .limit(3)
                .forEach(d -> System.out.println(d.getCalories()));
    }

    // find out whether the menu has a vegetarian option
    static void m06() {

        boolean hasIt =
            menu.stream()
                .anyMatch(Dish::isVegetarian);
    }

    // find out whether the menu is healthy (that is, all dishes are below 1000 calories)
    static void m07() {

        menu.stream().allMatch(d -> d.getCalories() < 1000);
        // or
        menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    // How would you count the number of dishes in a stream using the map and reduce methods?
    static void m08() {

        int total =
            menu.stream().mapToInt(d -> 1)
                .sum();

        System.out.println(total);

        total = menu.stream()
                    .reduce(0, (n, d) -> n+1, Integer::sum);

        System.out.println(total);
    }


    public static void main(String[] args) {
        m08();
    }

}
