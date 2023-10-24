package com.company.lombokdemo;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        Pracownik pracownik = new Pracownik("Jan","Kowalski", "1234",new BigDecimal("5000"));
        System.out.println(pracownik);
    }
}
