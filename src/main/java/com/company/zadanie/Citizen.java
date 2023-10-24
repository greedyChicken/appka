package com.company.zadanie;

import java.time.LocalDate;

public abstract class Citizen {
    private final String name;
    private final String surname;
    private final LocalDate birthdate;
    private final String pesel;

    public Citizen(String name, String surname, LocalDate birthdate, String pesel) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public String getPesel() {
        return pesel;
    }
}
