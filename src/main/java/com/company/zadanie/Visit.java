package com.company.zadanie;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Visit {
    private final Patient patient;
    private final Doctor doctor;
    private final LocalDate date;

    public Visit(Patient patient, Doctor doctor, LocalDate date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;

        doctor.addVisit(this);
        patient.addVisit(this);
    }
}
