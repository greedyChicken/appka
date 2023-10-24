package com.company;

import org.apache.commons.lang3.StringUtils;

public class StringUtilsDemo {
    public static void main(String[] args) {

        // isBlank
        System.out.println(StringUtils.isBlank("bla bla"));
        System.out.println(StringUtils.swapCase("Hello, World!"));

        //1
        // Utwórz funkcję, która przyjmuje dwa argumenty: tekst źródłowy i słowo. Funkcja ma zwracać tekst źródłowy,
        // w którym wszystkie wystąpienia słowa są podkreślone (np. za pomocą gwiazdek). Wykorzystaj metody z StringUtils do realizacji zadania.

        //2
        //Utwórz funkcję, która przyjmuje tekst i zwraca jego skrót, tj. pierwsze litery każdego słowa w tekście.
        // Na przykład dla "Hello World" zwróci "HW". Wykorzystaj metody z StringUtils.

        //3
        // Zadanie:
        //Stworzyć funkcję, która przyjmuje listę imion. Funkcja ma zwrócić string w formacie:
        // "Imię1, Imię2 i Imię3", bez względu na liczbę imion w liście. Jeśli lista jest pusta, zwróć "Brak imion".
        // Wykorzystaj StringUtils.join() do tego zadania.
        //
        //Przykład:
        //Dla listy ["Anna", "Bartek", "Celina"] funkcja powinna zwrócić "Anna, Bartek i Celina".

        //4
        // Porównywanie obiektów z wykorzystaniem ObjectUtils
        //
        //Biblioteka Apache Commons oferuje również klasę ObjectUtils, która zawiera użyteczne metody do pracy z obiektami.
        //
        //Zadanie:
        //Stworzyć funkcję, która przyjmuje dwa obiekty typu Integer i zwraca większą wartość. Jeśli jeden z obiektów
        // jest null, funkcja powinna zwrócić drugi obiekt. Jeśli oba obiekty są null, funkcja powinna zwrócić null.
        // Wykorzystaj ObjectUtils.max() do tego zadania.

        //5
        // Ćwiczenie 4: Manipulacja datą i czasem z wykorzystaniem DateUtils
        //
        //DateUtils to kolejna przydatna klasa z biblioteki Apache Commons, oferująca różne metody do pracy z datami.
        //
        //Zadanie:
        //Stworzyć funkcję, która przyjmuje datę i zwraca datę za dokładnie 7 dni. Wykorzystaj DateUtils.addDays() do tego zadania.
        //
        //Dodatkowe wyzwanie:
        //Stworzyć funkcję, która przyjmuje dwie daty i sprawdza, czy obie daty są tego samego dnia miesiąca, nie biorąc
        // pod uwagę roku. Wykorzystaj DateUtils.isSameDay() oraz klasę Calendar do ekstrakcji dnia miesiąca.
    }
}
