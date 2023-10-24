package com.company.lombokdemo;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Pracownik {

    String imie;
    String nazwisko;
    String PESEL;
    BigDecimal zarobki;



}
