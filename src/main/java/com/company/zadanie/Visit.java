package com.company.zadanie;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Visit {
    private static List<Visit> visitList = new ArrayList<>();
    private Doctor doctor;
    private Patient patient;

    private Date visitDate;

    public Visit(Doctor doctor,Patient patient, Date visitDate) {
        this.visitDate = visitDate;
        this.doctor = doctor;
        this.patient = patient;
        doctor.addVisit(this);
        patient.addVisit(this);
        visitList.add(this);
    }

    public static long busiestYear() {
        long requestedYear = 0;
        for (Visit v1 : visitList) {
            long checkedYear = v1.visitDate.getYear();
            int max = 0;
            int counter = 0;
            for (Visit v2 : visitList) {
                counter = (checkedYear == v2.visitDate.getYear()) ? counter + 1 : counter;
            }
            requestedYear = (counter > max) ? checkedYear : requestedYear;

        }
        return requestedYear;
    }
    public static void upload() {
        String fileName = "wizyty.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Assuming the first line is headers and should be skipped
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                int doctorId = Integer.parseInt(values[0]);
                int patientId = Integer.parseInt(values[1]);
                String visitDateString = values[2];
                String[] valuesDate = visitDateString.split("-");
                int year = Integer.parseInt(valuesDate[0]);
                int month = Integer.parseInt(valuesDate[1]) - 1;
                int day = Integer.parseInt(valuesDate[2]);

                Calendar cal = Calendar.getInstance();
                cal.set(year, month, day, 0, 0, 0);
                Date visitDate = cal.getTime();

                new Visit(Doctor.getDoctorById(doctorId), Patient.getPatientById(patientId), visitDate);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
  /*  public static void upload() {
        String fileName = "wizyty.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                int doctorId = Integer.parseInt(values[0]);
                int patientId = Integer.parseInt(values[1]);
                String visitDateString = values[2];
                String[] valuesDate = visitDateString.split("-");
                int year = Integer.parseInt(valuesDate[0]);
                int month = Integer.parseInt(valuesDate[1]);
                int day = Integer.parseInt(valuesDate[2]);
                LocalDate visitDate = LocalDate.of(year, month, day);
                new Visit(Doctor.getDoctorById(doctorId), Patient.getPatientById(patientId), visitDate);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static List<Visit> getVisitList() {
        return visitList;
    }

    public static void setVisitList(List<Visit> visitList) {
        Visit.visitList = visitList;
    }



    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Visit::: " +
                "doctor: " + doctor +
                ", patient: " + patient +
                ", visitDate: " + visitDate;
    }
}
