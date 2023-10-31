package com.company;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StringUtilsDemo {
    public static void main(String[] args) {

        // isBlank
        System.out.println(StringUtils.isBlank("bla bla"));
        System.out.println(StringUtils.swapCase("Hello, World!"));

        //1
        // Utwórz funkcję, która przyjmuje dwa argumenty: tekst źródłowy i słowo. Funkcja ma zwracać tekst źródłowy,
        // w którym wszystkie wystąpienia słowa są podkreślone (np. za pomocą gwiazdek). Wykorzystaj metody z StringUtils do realizacji zadania.
        System.out.println("-=Zadanie 1=-");
        String textToCheck = "Matka tka jak tkaczka tka, a tkaczka tka jak matka tka.";
        String wordToHighlight = "tka";

        System.out.println(highlightTheWord(textToCheck, wordToHighlight));

        //2
        //Utwórz funkcję, która przyjmuje tekst i zwraca jego skrót, tj. pierwsze litery każdego słowa w tekście.
        // Na przykład dla "Hello World" zwróci "HW". Wykorzystaj metody z StringUtils.

        System.out.println("-=Zadanie 2=-");
        System.out.println(getAbbreviation("Hello World"));
        System.out.println(getAbbreviation(textToCheck));

        //3
        // Zadanie:
        //Stworzyć funkcję, która przyjmuje listę imion. Funkcja ma zwrócić string w formacie:
        // "Imię1, Imię2 i Imię3", bez względu na liczbę imion w liście. Jeśli lista jest pusta, zwróć "Brak imion".
        // Wykorzystaj StringUtils.join() do tego zadania.
        //
        //Przykład:
        //Dla listy ["Anna", "Bartek", "Celina"] funkcja powinna zwrócić "Anna, Bartek i Celina".
        System.out.println("-=Zadanie 3=-");
        List<String> noNames = Lists.newArrayList();
        List<String> names = Lists.newArrayList("Anna", "Bartek", "Celina");
        System.out.println(joinNames(noNames));
        System.out.println(joinNames(names));

        //4
        // Porównywanie obiektów z wykorzystaniem ObjectUtils
        //
        //Biblioteka Apache Commons oferuje również klasę ObjectUtils, która zawiera użyteczne metody do pracy z obiektami.
        //
        //Zadanie:
        //Stworzyć funkcję, która przyjmuje dwa obiekty typu Integer i zwraca większą wartość. Jeśli jeden z obiektów
        // jest null, funkcja powinna zwrócić drugi obiekt. Jeśli oba obiekty są null, funkcja powinna zwrócić null.
        // Wykorzystaj ObjectUtils.max() do tego zadania.

        System.out.println("-=Zadanie 4=-");
        System.out.println("We are comparing two Ints, let's take 10 and 20.");
        System.out.println("Comparing 10 and 20: " + getMax(10, 20));
        System.out.println("Comparing null and 20: " + getMax(null, 20));
        System.out.println("Comparing 10 and null: " + getMax(10, null));
        System.out.println("Comparing null and null: " + getMax(null, null));
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

        System.out.println("Zadanie 5=-");
        Date today = new Date();
        System.out.println("Today's Date: " + today);
        Date dateAfterSevenDays = addSevenDays(today);
        System.out.println("Date after 7 days: " + dateAfterSevenDays);

        Calendar calendar = Calendar.getInstance();
        calendar.set(1986, Calendar.OCTOBER, 31);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        Date dateToCompare = calendar.getTime();
        System.out.println("Comparing today's date with " + dateFormat.format(calendar.getTime()) + " to see if they're on the same day of the month...");
        System.out.println(areDatesOnSameDayOfMonth(today, dateToCompare) ? "They are on the same day of the month!" : "They are NOT on the same day of the month.");
    }

    //AD Zadanie 1
    public static String highlightTheWord(String text, String word) {
        return StringUtils.replace(text, word, "*" + word + "*");
    }

    //AD Zadanie 2
    public static String getAbbreviation(String text) {
        String[] words = StringUtils.split(text);
        StringBuilder abbreviation = new StringBuilder();
        for (String w : words) {
            abbreviation.append(w.charAt(0));
        }
        return abbreviation.toString().toUpperCase();
    }

    //AD Zadanie 3
    public static String joinNames(List<String> names) {
        if (names.isEmpty()) {
            return "Brak imion";
        }
        if (names.size() > 1) {
            String allButLast = StringUtils.join(names.subList(0, names.size() - 1), ", ");
            return allButLast + " i " + names.get(names.size() - 1);
        } else {
            return names.get(0);
        }
    }

    //AD Zadanie 4
    public static Integer getMax(Integer a, Integer b) {
        return ObjectUtils.max(a, b);
    }

    //AD Zadanie 5
    public static Date addSevenDays(Date date) {
        return DateUtils.addDays(date, 7);
    }

    public static boolean areDatesOnSameDayOfMonth(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        int day2 = calendar2.get(Calendar.DAY_OF_MONTH);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        Calendar currentYearDate1 = Calendar.getInstance();
        currentYearDate1.set(currentYear, calendar1.get(Calendar.MONTH), day1);

        Calendar currentYearDate2 = Calendar.getInstance();
        currentYearDate2.set(currentYear, calendar2.get(Calendar.MONTH), day2);

        return DateUtils.isSameDay(currentYearDate1.getTime(), currentYearDate2.getTime());
    }

}
