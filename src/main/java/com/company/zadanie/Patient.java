package com.company.zadanie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Patient extends Citizen {
    private final int id;
    private final List<Visit> visits = new ArrayList<>();

    public Patient(String name, String surname, LocalDate birthdate, String pesel, int patientId) {
        super(name, surname, birthdate, pesel);
        this.id = patientId;
    }

    public int getId() {
        return id;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void addVisit(Visit visit) {
        if (visits.contains(visit)) {
            throw new VisitExistsException("This visit is already assigned to the patient.");
        }
        visits.add(visit);
    }

    public int countVisitedDoctors() {
        HashSet<Doctor> doctors = new HashSet<>();
        for (Visit visit : getVisits()) {
            doctors.add(visit.getDoctor());
        }
        return doctors.size();
    }
}
