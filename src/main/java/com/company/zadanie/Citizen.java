package com.company.zadanie;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public abstract class Citizen {
    private final String name;
    private final String surname;
    private final LocalDate birthdate;
    private final String pesel;
}
