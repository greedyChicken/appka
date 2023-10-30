package com.company;

import com.google.common.base.Joiner;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;

import java.util.List;

public class GuavaDemo {

    public static void main(String[] args) {

        // Biblioteka Guava od Google jest jednym z najbardziej wszechstronnych narzędzi dostępnych dla programistów Javy.
        // Oto cztery zadania, które pomogą Twoim uczniom zrozumieć niektóre z jej kluczowych funkcji:
        System.out.println(convertListToMultiset(List.of("kot", "pies", "kot", "ryba")));
        System.out.println(convertNameListToNameString(List.of("Anna", "Bartek", "Celina")));
    }
    //1
    // Ćwiczenie 1: Multizbiory z wykorzystaniem Guava
    //
    //Zadanie:
    //Stworzyć funkcję, która przyjmuje listę słów i zwraca multizbiór (Multiset) z liczbą wystąpień każdego słowa w liście. Wykorzystaj HashMultiset z Guavy.
    //
    //Przykład:
    //Dla listy ["kot", "pies", "kot", "ryba"] funkcja powinna zwrócić multizbiór, w którym "kot" występuje 2 razy, "pies" 1 raz i "ryba" 1 raz.
    public static Multiset<String> convertListToMultiset(List<String> list) {
        Multiset<String> multiset = HashMultiset.create();
        multiset.addAll(list);
        return multiset;
    }

    //2
    // Ćwiczenie 2: Tworzenie niemutowalnych zbiorów
    //
    //Zadanie:
    //Stworzyć funkcję, która przyjmuje listę liczb i zwraca niemutowalny zbiór tych liczb, usuwając duplikaty. Wykorzystaj ImmutableSet z Guavy.
    public static ImmutableSet<String> convertListToImmutableset(List<String> list) {
        return ImmutableSet.copyOf(list);
    }

    //4
    // Ćwiczenie 4: Łączenie stringów z wykorzystaniem Joiner
    //
    //Zadanie:
    //Stworzyć funkcję, która przyjmuje listę imion i zwraca string, w którym imiona są oddzielone przecinkiem i spacją,
    // a przed ostatnim imieniem znajduje się słowo "i". Jeśli lista jest pusta, funkcja powinna zwrócić "Brak imion". Wykorzystaj Joiner z Guavy.
    //
    //Przykład:
    //Dla listy ["Anna", "Bartek", "Celina"] funkcja powinna zwrócić "Anna, Bartek i Celina".
    public static String convertNameListToNameString(List<String> list) {
        if (list.isEmpty()) {
            return "Brak imion";
        } else if (list.size() == 1) {
            return list.get(0);
        }
        Joiner mainJoiner = Joiner.on(", ");
        Joiner secondaryJoiner = Joiner.on(" i ");
        return secondaryJoiner.join(mainJoiner.join(list.subList(0, list.size()-1)), list.get(list.size()-1));
    }
}
