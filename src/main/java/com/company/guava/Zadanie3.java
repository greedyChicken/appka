package com.company.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

//3
// Ćwiczenie 3: Zastosowanie Cache z Guava
//
//Zadanie:
//Stworzyć prostą aplikację, która korzysta z cache do przechowywania wyników operacji.
// Aplikacja powinna obliczać kwadrat liczby. Jeśli dana liczba została już wcześniej przetworzona,
// aplikacja powinna korzystać z cache, zamiast obliczać wynik ponownie. Wykorzystaj CacheBuilder z Guavy.

public class Zadanie3 {
    private final Cache<Integer, Integer> cache;
    public Zadanie3() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(Integer.MAX_VALUE)
                .build();
    }

    public int square(int number) {
        Integer result = cache.getIfPresent(number);

        if (result == null) {
            result = number * number;
//            System.out.println("wynik jest liczony");
            cache.put(number, result);
        }

        return result;
    }
}
