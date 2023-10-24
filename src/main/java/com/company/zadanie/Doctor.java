package com.company.zadanie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Doctor extends Citizen {
    private final int id;
    private final String specialty;
    private final String nip;
    private final List<Visit> visits = new ArrayList<>();

    public Doctor(String name, String surname, LocalDate birthdate, String pesel, int doctorId, String specialty, String nip) {
        super(name, surname, birthdate, pesel);
        this.id = doctorId;
        this.specialty = specialty;
        this.nip = nip;
    }

    public int getId() {
        return id;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getNip() {
        return nip;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void addVisit(Visit visit) {
        if (visits.contains(visit)) {
            throw new VisitExistsException("This visit is already assigned to the doctor.");
        }
        visits.add(visit);
    }

    public int countPatients() {
        HashSet<Patient> patients = new HashSet<>();
        for (Visit visit : getVisits()) {
            patients.add(visit.getPatient());
        }
        return patients.size();
    }
}
