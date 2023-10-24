package com.company;

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

        //2
        // Ćwiczenie 2: Tworzenie niemutowalnych zbiorów
        //
        //Zadanie:
        //Stworzyć funkcję, która przyjmuje listę liczb i zwraca niemutowalny zbiór tych liczb, usuwając duplikaty. Wykorzystaj ImmutableSet z Guavy.

        //3
        // Ćwiczenie 3: Zastosowanie Cache z Guava
        //
        //Zadanie:
        //Stworzyć prostą aplikację, która korzysta z cache do przechowywania wyników operacji.
        // Aplikacja powinna obliczać kwadrat liczby. Jeśli dana liczba została już wcześniej przetworzona,
        // aplikacja powinna korzystać z cache, zamiast obliczać wynik ponownie. Wykorzystaj CacheBuilder z Guavy.

        //4
        // Ćwiczenie 4: Łączenie stringów z wykorzystaniem Joiner
        //
        //Zadanie:
        //Stworzyć funkcję, która przyjmuje listę imion i zwraca string, w którym imiona są oddzielone przecinkiem i spacją,
        // a przed ostatnim imieniem znajduje się słowo "i". Jeśli lista jest pusta, funkcja powinna zwrócić "Brak imion". Wykorzystaj Joiner z Guavy.
        //
        //Przykład:
        //Dla listy ["Anna", "Bartek", "Celina"] funkcja powinna zwrócić "Anna, Bartek i Celina".
    }
}
