package com.company.zadanie;

import com.company.zadanie.exceptions.DoctorDoesNotExistException;
import com.company.zadanie.exceptions.PatientDoesNotExistException;

import java.io.File;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Doctor> doctors = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();
        List<Visit> visits = new ArrayList<>();

        String doctorsFilePath = "lekarze.txt";
        String patientsFilePath = "pacjenci.txt";
        String visitsFilePath = "wizyty.txt";

        try (Scanner doctorsScanner = new Scanner(new File(doctorsFilePath));
            Scanner patientsScanner = new Scanner(new File(patientsFilePath));
            Scanner visitsScanner = new Scanner(new File(visitsFilePath))) {
            doctorsScanner.nextLine();
            while (doctorsScanner.hasNextLine()) {
                String line = doctorsScanner.nextLine();
                String[] words = line.split("\t");
                if (words.length > 0) {
                    doctors.add(convertDoctorData(words));
                }
            }
            patientsScanner.nextLine();
            while (patientsScanner.hasNextLine()) {
                String line = patientsScanner.nextLine();
                String[] words = line.split("\t");
                if (words.length > 0) {
                    patients.add(convertPatientData(words));
                }
            }
            visitsScanner.nextLine();
            while (visitsScanner.hasNextLine()) {
                String line = visitsScanner.nextLine();
                String[] words = line.split("\t");
                if (words.length > 0) {
                    visits.add(convertVisitData(words, doctors, patients));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Doctor doctorWithTheMostVisits = getDoctorWithTheMostVisits(doctors);
        System.out.println(doctorWithTheMostVisits.getName() + " " + doctorWithTheMostVisits.getSurname());

        Patient patientWithTheMostVisits = getPatientWithTheMostVisits(patients);
        System.out.println(patientWithTheMostVisits.getName() + " " + patientWithTheMostVisits.getSurname());

        System.out.println(getTheMostPopularSpecialty(doctors));
        System.out.println(getYearWithTheMostVisits(visits));
        for (Doctor doctor : getTopFiveOldestDoctors(doctors)) {
            System.out.println(doctor.getName() + " " + doctor.getSurname());
        }
        for (Patient patient : getPatientsThatVisitedFiveDoctorsAndMore(patients)) {
            System.out.println(patient.getName() + " " + patient.getSurname());
        }
        for (Doctor doctor : getExclusiveDoctors(doctors)) {
            System.out.println(doctor.getName() + " " + doctor.getSurname());
        }
    }

    private static Doctor getDoctorWithTheMostVisits(List<Doctor> doctors) {
        if (doctors.isEmpty()) {
            throw new DoctorDoesNotExistException("Such doctor does not exist.");
        }
        Doctor hasTheMostVisits = doctors.get(0);
        for (Doctor doctor : doctors) {
            if (doctor.getVisits().size() > hasTheMostVisits.getVisits().size()) {
                hasTheMostVisits = doctor;
            }
        }
        return hasTheMostVisits;
    }

    private static Patient getPatientWithTheMostVisits(List<Patient> patients) {
        if (patients.isEmpty()) {
            throw new PatientDoesNotExistException("Such patient does not exist.");
        }
        Patient hasTheMostVisits = patients.get(0);
        for (Patient patient : patients) {
            if (patient.getVisits().size() > hasTheMostVisits.getVisits().size()) {
                hasTheMostVisits = patient;
            }
        }
        return hasTheMostVisits;
    }

    private static String getTheMostPopularSpecialty(List<Doctor> doctors) {
        HashMap<String, Integer> specialties = new HashMap<>();
        for (Doctor doctor : doctors) {
            String specialty = doctor.getSpecialty();
            if (specialties.containsKey(specialty)) {
                specialties.put(specialty, specialties.get(specialty) + 1);
            } else {
                specialties.put(specialty, 1);
            }
        }
        return Collections.max(specialties.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private static int getYearWithTheMostVisits(List<Visit> visits) {
        HashMap<Integer, Integer> years = new HashMap<>();
        for (Visit visit : visits) {
            int year = visit.getDate().getYear();
            if (years.containsKey(year)) {
                years.put(year, years.get(year) + 1);
            } else {
                years.put(year, 1);
            }
        }
        return Collections.max(years.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private static List<Doctor> getTopFiveOldestDoctors(List<Doctor> doctors) {
        List<Doctor> sortedDoctors = new ArrayList<>(doctors);
        sortedDoctors.sort(Comparator.comparing(Doctor::getBirthdate, Comparator.comparingInt(LocalDate::getYear))
                                    .thenComparing(Doctor::getBirthdate, Comparator.comparingInt(LocalDate::getMonthValue))
                                    .thenComparing(Doctor::getBirthdate, Comparator.comparingInt(LocalDate::getDayOfMonth)));

        return sortedDoctors.subList(0,5);
    }

    private static List<Patient> getPatientsThatVisitedFiveDoctorsAndMore(List<Patient> patients) {
        List<Patient> wantedPatients = new ArrayList<>();
        for (Patient patient : patients) {
            if (patient.countVisitedDoctors() >= 5) {
                wantedPatients.add(patient);
            }
        }
        return wantedPatients;
    }

    private static List<Doctor> getExclusiveDoctors(List<Doctor> doctors) {
        List<Doctor> exclusiveDoctors = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.countPatients() == 1) {
                exclusiveDoctors.add(doctor);
            }
        }
        return exclusiveDoctors;
    }

    private static Doctor convertDoctorData(String[] data) {
        int doctorId = Integer.parseInt(data[0]);
        String surname = data[1];
        String name = data[2];
        String specialty = data[3];
        LocalDate birthdate = convertDateDataToLocalDate(data[4].split("-"));
        String nip = data[5];
        String pesel = data[6];

        return new Doctor(name, surname, birthdate, pesel, doctorId, specialty, nip);
    }

    private static Patient convertPatientData(String[] data) {
        int patientId = Integer.parseInt(data[0]);
        String surname = data[1];
        String name = data[2];
        String pesel = data[3];
        LocalDate birthdate = convertDateDataToLocalDate(data[4].split("-"));

        return new Patient(name, surname, birthdate, pesel, patientId);
    }

    private static Visit convertVisitData(String[] data, List<Doctor> doctors, List<Patient> patients) {
        Doctor doctor = getDoctorById(doctors, Integer.parseInt(data[0]));
        Patient patient = getPatientById(patients, Integer.parseInt(data[1]));
        LocalDate visitDate = convertDateDataToLocalDate(data[2].split("-"));
        return new Visit(patient, doctor, visitDate);
    }

    private static Doctor getDoctorById(List<Doctor> doctors, int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        throw new DoctorDoesNotExistException("There is no doctor with such id.");
    }

    private static Patient getPatientById(List<Patient> patients, int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        throw new PatientDoesNotExistException("There is no patient with such id.");
    }

    private static LocalDate convertDateDataToLocalDate(String[] data) {
        int year = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]);
        int day = Integer.parseInt(data[2]);
        return LocalDate.of(year, month, day);
    }
}
