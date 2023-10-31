package com.company.zadanie;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Patient {
    private static List<Patient> patientList = new ArrayList<>();
    private int id;
    private String lastName;
    private String firstName;
    private String pesel;
    private Date birthDate;
    private List<Visit> visits = new ArrayList<>();

    public Patient(int id, String lastName, String firstName, String pesel, Date birthDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.pesel = pesel;
        this.birthDate = birthDate;
        patientList.add(this);
    }

    public static Patient getPatientById(int patientId) {
        Patient requestedPatient = null;
        for (Patient p : Patient.getPatientList()) {
            requestedPatient = (p.getId() == patientId) ? p : requestedPatient;
        }
        return requestedPatient;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }
    public static Patient withTheMostVisit() {
        Patient newPatient = null;
        int countMax = 0;
        for (Patient p : patientList) {
            int countVisit = p.visits.size();
            if (countVisit > countMax) {
                countMax = countVisit;
                newPatient = p;
            }
        }
        return newPatient;
    }
    public static List<Patient> patientsHasVisitSince2000() {
        List<Patient> requestedPatients = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(2000, Calendar.JANUARY, 1);
        Date dateSince2000 = cal.getTime();

        for (Visit v : Visit.getVisitList()) {
            if (v.getVisitDate().after(dateSince2000)) {
                requestedPatients.add(v.getPatient());
            }
        }
        return requestedPatients;
    }

   /* public static List<Patient> patientsHasVisitSince2000() {
        List<Patient> requestedPatients = new ArrayList<>();
        LocalDate date = LocalDate.of(2000, 1, 1);
        for (Visit v : Visit.getVisitList()) {
            if (v.getVisitDate().isAfter(date)) {
                requestedPatients.add(v.getPatient());
            }
        }
        return requestedPatients;
    }*/
   public static void upload() {
       String fileName = "pacjenci.txt";
       try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
           String line;
           br.readLine();
           while ((line = br.readLine()) != null) {
               String[] values = line.split("\t");
               int id = Integer.parseInt(values[0]);
               String lastName = values[1];
               String firstName = values[2];
               String pesel = values[3];
               String birthDateString = values[4];
               String[] valuesDate = birthDateString.split("-");
               int year = Integer.parseInt(valuesDate[0]);
               int month = Integer.parseInt(valuesDate[1]);
               int day = Integer.parseInt(valuesDate[2]);
               Calendar calendar = Calendar.getInstance();
               calendar.set(year, month - 1, day);
               Date birthDate = calendar.getTime();
               new Patient(id, lastName, firstName, pesel, birthDate);
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }

  /*  public static void upload() {
        String fileName = "pacjenci.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                int id = Integer.parseInt(values[0]);
                String lastName = values[1];
                String firstName = values[2];
                String pesel = values[3];
                String birthDateString = values[4];
                String[] valuesDate = birthDateString.split("-");
                int year = Integer.parseInt(valuesDate[0]);
                int month = Integer.parseInt(valuesDate[1]);
                int day = Integer.parseInt(valuesDate[2]);
                LocalDate birthDate = LocalDate.of(year, month, day);
                new Patient(id, lastName, firstName, pesel, birthDate);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public static void printAllPatients() {
        for (Patient p : patientList) {
            System.out.println(p.toString());
        }
    }

    public static List<Patient> getPatientList() {
        return patientList;
    }

    public static void setPatientList(List<Patient> patientList) {
        Patient.patientList = patientList;
    }

    @Override
    public String toString() {
        return "Patient:: " +
                "id: " + id +
                ", lastName: '" + lastName + '\'' +
                ", firstName: '" + firstName + '\'' +
                ", pesel: '" + pesel + '\'' +
                ", birthDate: " + birthDate;
    }
}
