package com.company;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.google.common.base.Joiner;
import java.util.List;
import java.util.Random;

public class GuavaDemo {

    public static void main(String[] args) {

        // Biblioteka Guava od Google jest jednym z najbardziej wszechstronnych narzędzi dostępnych dla programistów Javy.
        // Oto cztery zadania, które pomogą Twoim uczniom zrozumieć niektóre z jej kluczowych funkcji:

        //1
        // Ćwiczenie 1: Multizbiory z wykorzystaniem Guava
        //
        //Zadanie:
        //Stworzyć funkcję, która przyjmuje listę słów i zwraca multizbiór (Multiset) z liczbą wystąpień każdego słowa w liście. Wykorzystaj HashMultiset z Guavy.
        //
        //Przykład:
        //Dla listy ["kot", "pies", "kot", "ryba"] funkcja powinna zwrócić multizbiór, w którym "kot" występuje 2 razy, "pies" 1 raz i "ryba" 1 raz.
        List<String> animals = Lists.newArrayList("kot", "pies", "kot", "ryba");
        System.out.println("-=Zadanie 1=-");
        System.out.println(wordsToMultiset(animals));
        //2
        // Ćwiczenie 2: Tworzenie niemutowalnych zbiorów
        //
        //Zadanie:
        //Stworzyć funkcję, która przyjmuje listę liczb i zwraca niemutowalny zbiór tych liczb, usuwając duplikaty. Wykorzystaj ImmutableSet z Guavy.
        List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 11, 12, 12, 14, 13);
        System.out.println("-=Zadanie 2=-");
        System.out.println(listToImmutableSet(numbers));
        //3
        // Ćwiczenie 3: Zastosowanie Cache z Guava
        //
        //Zadanie:
        //Stworzyć prostą aplikację, która korzysta z cache do przechowywania wyników operacji.
        // Aplikacja powinna obliczać kwadrat liczby. Jeśli dana liczba została już wcześniej przetworzona,
        // aplikacja powinna korzystać z cache, zamiast obliczać wynik ponownie. Wykorzystaj CacheBuilder z Guavy.

        System.out.println("-=Zadanie 3=-");
        Cache<Integer, Integer> cache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .build();

        int calculationCount = 0;
        int cacheHitCount = 0;

        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int number = random.nextInt(150);

            Integer result = cache.getIfPresent(number);
            if (result == null) {
                calculationCount++;
                result = number * number;
                cache.put(number, result);
            } else {
                cacheHitCount++;
            }
        }
        System.out.println("Number of calculations: " + calculationCount);
        System.out.println("Number of cache hits: " + cacheHitCount);
        //4
        // Ćwiczenie 4: Łączenie stringów z wykorzystaniem Joiner
        //
        //Zadanie:
        //Stworzyć funkcję, która przyjmuje listę imion i zwraca string, w którym imiona są oddzielone przecinkiem i spacją,
        // a przed ostatnim imieniem znajduje się słowo "i". Jeśli lista jest pusta, funkcja powinna zwrócić "Brak imion". Wykorzystaj Joiner z Guavy.
        //
        //Przykład:
        //Dla listy ["Anna", "Bartek", "Celina"] funkcja powinna zwrócić "Anna, Bartek i Celina".
        System.out.println("-=Zadanie 4=-");
        List<String> noNames = Lists.newArrayList();
        List<String> names = Lists.newArrayList("Anna", "Bartek", "Celina");
        System.out.println(joinNames(noNames));
        System.out.println(joinNames(names));
    }

    //AD ZADANIE 1
    public static Multiset<String> wordsToMultiset(List<String> words) {
        return HashMultiset.create(words);
    }

    //AD ZADANIE 2
    public static ImmutableSet<Integer> listToImmutableSet(List<Integer> numbers) {
        return ImmutableSet.copyOf(numbers);
    }

    //AD ZADANIE 4
    public static String joinNames(List<String> names) {
        if (names.isEmpty()) {
            return "Brak imion";
        }
        Joiner joiner = Joiner.on(", ");
        if (names.size() > 1) {
            String allButLast = joiner.join(names.subList(0, names.size() - 1));
            return allButLast + " i " + names.get(names.size() - 1);
        } else {
            return names.get(0);
        }
    }
}
