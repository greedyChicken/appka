package com.company.zadanie;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        /*
         * Dane są trzy pliki tekstowe o nazwach: lekarze.txt, pacjenci.txt, wizyty.txt.
         *
         * Zawierają one informacje na temat lekarzy, pacjentów i odbytych wizyt
         * domowych.
         * W każdym z plików dane w wierszu oddzielone są znakami tabulacji.
         *
         * Plik o nazwie lekarze.txt zawiera informacje na temat lekarzy: numer
         * identyfikacyjny
         *
         * lekarza, jego nazwisko, imię, specjalność, datę urodzenia, numer NIP i numer
         * PESEL.
         *
         * Przykład:
         *
         * 23 Kadaj Monika pediatra  1965-03-16 879-122-69-94 65031687654
         * 34 Nowak Anna   nefrolog  1965-03-16 879-122-69-94 65031687654
         *
         * Plik o nazwie pacjenci.txt zawiera dane na temat pacjentów: numer
         * identyfikacyjny
         *
         * pacjenta, jego nazwisko, imię, numer PESEL i datę urodzenia.
         *
         * Przykład:
         * 122 Nowakowska Joanna 73050512356 1973-05-05
         *
         * 124 Witkowski  Hubert 88030422345 1988-03-04
         *
         * Plik o nazwie wizyty.txt zawiera informacje na temat domowych wizyt
         * lekarskich
         *
         * przeprowadzonych przez lekarzy u pacjentów: numer identyfikacyjny lekarza,
         * numer
         *
         * identyfikacyjny pacjenta oraz datę wizyty lekarskiej przeprowadzonej przez
         * lekarza
         *
         * u pacjenta.
         *
         * Przykład:
         *
         * 23 124 2006-12-13
         *
         * 34 122 2007-02-20
         * Wykorzystując informacje zawarte w plikach wykonaj następujące polecenia:
         *
         * Daty:
         *
         * Wypisz wszystkich pacjentów którzy mieli wizyty od 2000 roku
         *  Wypisz wszystkich lekarzy którzy mieli wizyty w marcu, czerwcu i grudniu 2007
         *  Wypisz lekarza ktory miał najwiecej wizyt w okresie podanym jako parametr
         * Dodatek:
         *
         * - znajdź lekarza ktory miał najwięcej wizyt
         *
         * - znajdź pacjenta który miał najwięcej wizyt
         *
         * - która specalizacja cieszy się największym ppowodzeniem
         *
         * - którego roku było najwięcej wizyt?
         *
         * - wypisz top 5 najstarszych lekarzy
         *
         *
         */

        Doctor.upload();
        Patient.upload();
        Visit.upload();


        System.out.println(Doctor.getDoctorList().size());
        Doctor.printAllDoctors();
        System.out.println();
        System.out.println();
        System.out.println(Patient.patientsHasVisitSince2000());
        System.out.println(Doctor.hasVisitInMCL2007());
        System.out.println("Doctor with the highest number of visits in period of requested time is: ");
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.set(2000, 0, 1);  // January is month 0 in Calendar
        Date startDateUtil = calendarStart.getTime();

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(2007, 0, 1);
        Date endDateUtil = calendarEnd.getTime();

        System.out.println(Doctor.withTheMostVisitsInPeriod(startDateUtil, endDateUtil));
        //LocalDate startDate = LocalDate.of(2000, 01, 01);
        //LocalDate endDate = LocalDate.of(2007,01,01);
        //System.out.println(Doctor.withTheMostVisitsInPeriod(startDate,endDate));
        System.out.println("Doctor with the highest number of visits is: ");
        System.out.println(Doctor.withTheMostVisits());
        System.out.println("Patient with the highest number of visits is: ");
        System.out.println(Patient.withTheMostVisit());
        System.out.println(Doctor.theMostCommonSpecialization());
        System.out.println(Visit.busiestYear());
        System.out.println(Doctor.top5TheOldest());





    }
}
