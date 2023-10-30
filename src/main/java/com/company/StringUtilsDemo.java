package com.company;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StringUtilsDemo {
    public static void main(String[] args) {

        // isBlank
        System.out.println(StringUtils.isBlank("bla bla"));
        System.out.println(StringUtils.swapCase("Hello, World!"));
        System.out.println(formatNames(List.of("Anna", "Bartek", "Celina")));

        System.out.println(isSameDayOfMonth(new Date(2021, Calendar.JANUARY, 12), new Date(2020, Calendar.JANUARY, 12)));

    }

    //1
    // Utwórz funkcję, która przyjmuje dwa argumenty: tekst źródłowy i słowo. Funkcja ma zwracać tekst źródłowy,
    // w którym wszystkie wystąpienia słowa są podkreślone (np. za pomocą gwiazdek). Wykorzystaj metody z StringUtils do realizacji zadania.
    public static String emphasizeGivenWord(String sourceText, String word) {
        if (StringUtils.isEmpty(sourceText) || StringUtils.isEmpty(word)) {
            return sourceText;
        }

        return StringUtils.replace(sourceText, word, "*" + word + "*");
    }

    //2
    //Utwórz funkcję, która przyjmuje tekst i zwraca jego skrót, tj. pierwsze litery każdego słowa w tekście.
    // Na przykład dla "Hello World" zwróci "HW". Wykorzystaj metody z StringUtils.
    public static String getShortenText(String text) {
        return StringUtils.abbreviate(text, Integer.MAX_VALUE);
    }

    //3
    // Zadanie:
    //Stworzyć funkcję, która przyjmuje listę imion. Funkcja ma zwrócić string w formacie:
    // "Imię1, Imię2 i Imię3", bez względu na liczbę imion w liście. Jeśli lista jest pusta, zwróć "Brak imion".
    // Wykorzystaj StringUtils.join() do tego zadania.
    //
    //Przykład:
    //Dla listy ["Anna", "Bartek", "Celina"] funkcja powinna zwrócić "Anna, Bartek i Celina".

    public static String formatNames(List<String> nameList) {
        if (nameList.isEmpty()) {
            return "Brak imion";
        } else if (nameList.size() == 1) {
            return nameList.get(0);
        }

        return StringUtils.join(nameList.subList(0, nameList.size()-1), ", ") + " i " + nameList.get(nameList.size() - 1);
    }

    //4
    // Porównywanie obiektów z wykorzystaniem ObjectUtils
    //
    //Biblioteka Apache Commons oferuje również klasę ObjectUtils, która zawiera użyteczne metody do pracy z obiektami.
    //
    //Zadanie:
    //Stworzyć funkcję, która przyjmuje dwa obiekty typu Integer i zwraca większą wartość. Jeśli jeden z obiektów
    // jest null, funkcja powinna zwrócić drugi obiekt. Jeśli oba obiekty są null, funkcja powinna zwrócić null.
    // Wykorzystaj ObjectUtils.max() do tego zadania.
    public static Integer getBiggerInteger(Integer number1, Integer number2) {
        if (number1 == null && number2 != null) {
            return number2;
        } else if (number1 != null && number2 == null) {
            return number1;
        }
        return ObjectUtils.max(number1, number2);
    }

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
    public static Date get7DaysForward(Date date) {
        return DateUtils.addDays(date, 7);
    }

    public static boolean isSameDayOfMonth(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int date1Month = cal.get(Calendar.MONTH);
        int date1Day = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(date2);
        int date2Month = cal.get(Calendar.MONTH);
        int date2Day = cal.get(Calendar.DAY_OF_MONTH);
        return date1Month == date2Month && date1Day == date2Day;
    }
}
