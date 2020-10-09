package exes01;

import study.domain.Trader;
import study.domain.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Transactions {

    static Trader mario = new Trader("Mario","Milan");
    static Trader alan = new Trader("Alan","Cambridge");
    static Trader brian = new Trader("Brian","Cambridge");
    static Trader raoul = new Trader("Raoul", "Cambridge");

    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    // find tx with smallest value
    static void m01() {

         Optional<Integer> r =
             transactions.stream().map(Transaction::getValue)
                .sorted()
                .findFirst();

        System.out.println(r.orElseThrow(RuntimeException::new));
    }

    // find tx highest value
    static void m02() {

        Optional<Integer> r =
            transactions.stream()
                .map(Transaction::getValue)
                .sorted(Comparator.reverseOrder())
                .findFirst();
        System.out.println(r.orElseThrow(RuntimeException::new));
    }

    // print all tx values from traders living in Cambridge
    static void m03() {

        transactions.stream()
                .filter(tx -> tx.getTrader().getCity().equals("Cambridge"))
                .forEach(tx -> System.out.println(tx.getValue()));
    }

    // are any trader based in Milan?
    static void m04() {

        Optional<Transaction> r =
            transactions.stream()
                .filter(tx -> tx.getTrader().getCity().equals("Milan"))
                .findFirst();
        System.out.println(r.isPresent());
    }

    // Return a string of all traders’ names sorted alphabetically.
    static void m05() {

        StringBuilder r =
            transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .distinct()
                .sorted()
                //.reduce(String::concat).get();
                .reduce(new StringBuilder(), StringBuilder::append, StringBuilder::append);
        System.out.println(r);
    }

    // Find all traders from Cambridge and sort them by name
    static void m06() {

        transactions.stream()
                .map(Transaction::getTrader)
                .filter(tr -> tr.getCity().equals("Cambridge"))
                .map(Trader::getName)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    // What are all the unique cities where the traders work?
    static void m07() {

        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);
    }

    // Find all transactions in the year 2011 and sort them by value (small to high).
    static void m08() {

        transactions.stream()
                .filter(tx -> tx.getYear() == 2011)
                .sorted((t1, t2) -> t1.getValue() - t2.getValue())
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        m08();
    }
}
