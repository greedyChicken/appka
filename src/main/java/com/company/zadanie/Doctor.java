package com.company.zadanie;

import com.google.common.collect.Ordering;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Doctor {
    private static List<Doctor> doctorList = new ArrayList<>();
    private int id;
    private String lastName;
    private String firstName;
    private String specialty;
    private Date birthDate;
    private String nip;
    private String pesel;
    private List<Visit> visits = new ArrayList<>();
    private int age;

    public Doctor(int id, String lastName, String firstName, String specialty, Date birthDate, String nip, String pesel) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.specialty = specialty;
        this.birthDate = birthDate;
        this.nip = nip;
        this.pesel = pesel;
        this.age = this.getAge();
        doctorList.add(this);
    }


    public static List<Doctor> top5TheOldest() {
        Ordering<Doctor> byAge = Ordering.from((d1, d2) -> Integer.compare(d2.getAge(), d1.getAge()));
        return byAge.leastOf(doctorList, 5);
    }

/*    public static List<Doctor> top5TheOldest() {
        List<Doctor> sortedDoctors = new ArrayList<>(doctorList);
        for (int i = 0; i < sortedDoctors.size() - 1; i++) {
            for (int j = 0; j < sortedDoctors.size() - i - 1; j++) {
                Doctor d1 = sortedDoctors.get(j);
                Doctor d2 = sortedDoctors.get(j + 1);
                if (d1.getAge() < d2.getAge()) {
                    sortedDoctors.set(j, d2);
                    sortedDoctors.set(j + 1, d1);
                }
            }
        }

        return sortedDoctors.subList(0, Math.min(5, sortedDoctors.size()));
    }*/

    public static String theMostCommonSpecialization() {
        String requestedSpecialty = null;
        int countMax = 0;
        for (Doctor d1 : doctorList) {
            String checkedSpecialty = d1.specialty;
            int counter = 0;
            for (Doctor d2 : doctorList) {
                counter = (checkedSpecialty.equals(d2.specialty)) ? counter + 1 : counter;
            }
            if (counter > countMax) {
                countMax = counter;
                requestedSpecialty = checkedSpecialty;
            }
        }
        return requestedSpecialty;
    }
    public static void upload() {
        String fileName = "lekarze.txt";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                int id = Integer.parseInt(values[0]);
                String lastName = values[1];
                String firstName = values[2];
                String specialty = values[3];
                Date birthDate = simpleDateFormat.parse(values[4]);
                String nip = values[5];
                String pesel = values[6];
                new Doctor(id, lastName, firstName, specialty, birthDate, nip, pesel);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

/*    public static void upload() {
        String fileName = "lekarze.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split("\t");
                int id = Integer.parseInt(values[0]);
                String lastName = values[1];
                String firstName = values[2];
                String specialty = values[3];
                LocalDate birthDate = LocalDate.parse(values[4]);
                String nip = values[5];
                String pesel = values[6];
                new Doctor(id, lastName, firstName, specialty, birthDate, nip, pesel);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static Doctor withTheMostVisits() {
        Doctor newDoc = null;
        int countMax = 0;
        for (Doctor d : doctorList) {
            int countVisit = d.visits.size();
            if (countVisit > countMax) {
                countMax = countVisit;
                newDoc = d;
            }
        }
        return newDoc;
    }

    public static Doctor withTheMostVisitsInPeriod(Date dateStart, Date dateEnd) {
        Doctor newDoc = null;
        int countMax = 0;

        for (Doctor d : doctorList) {
            int countVisit = 0;
            for (Visit v : Visit.getVisitList()) {
                if (d.equals(v.getDoctor())) {
                    Date visitDate = v.getVisitDate();
                    if (visitDate.after(dateStart) && visitDate.before(dateEnd)) {
                        countVisit++;
                    }
                }
            }
            if (countVisit > countMax) {
                countMax = countVisit;
                newDoc = d;
            }
        }
        return newDoc;
    }


/*    public static Doctor withTheMostVisitsInPeriod(LocalDate dateStart, LocalDate dateEnd) {
        Doctor newDoc = null;
        int countMax = 0;
        for (Doctor d : doctorList) {
            int countVisit = 0;
            for (Visit v : Visit.getVisitList()) {
                if (d.equals(v.getDoctor())) {
                    countVisit++;
                }
            }
            if (countVisit > countMax) {
                countMax = countVisit;
                newDoc = d;
            }
        }
        return newDoc;
    }*/

    public static List<Doctor> hasVisitInMCL2007() {
        List<Doctor> requestedDoctors = new ArrayList<>();

        Calendar calStart = Calendar.getInstance();
        calStart.set(2007, Calendar.JANUARY, 1);
        Date dateStart = calStart.getTime();

        Calendar calEnd = (Calendar) calStart.clone();
        calEnd.add(Calendar.MONTH, 3);
        Date dateEnd = calEnd.getTime();

        for (Visit v : Visit.getVisitList()) {
            Date visitDate = v.getVisitDate();
            if (visitDate.after(dateStart) && visitDate.before(dateEnd)) {
                requestedDoctors.add(v.getDoctor());
            }
        }
        return requestedDoctors;
    }
/*    public static List<Doctor> hasVisitInMCL2007() {
        List<Doctor> requestedDoctors = new ArrayList<>();
        LocalDate dateStart = LocalDate.of(2007, 1, 1);
        LocalDate dateEnd = dateStart.plusMonths(3);
        for (Visit v : Visit.getVisitList()) {
            if (v.getVisitDate().isAfter(dateStart) && v.getVisitDate().isBefore(dateEnd)) {
                requestedDoctors.add(v.getDoctor());
            }
        }
        return requestedDoctors;
    }*/

    /*public int getYearFromPesel() {
        Pattern patternYear = Pattern.compile("(\\d{2})(\\d{2})\\d{7}");
        Matcher matcher = patternYear.matcher(this.pesel);
        matcher.matches();
        int year = Integer.parseInt(matcher.group(1));
        int month = Integer.parseInt(matcher.group(2));
        if (month > 20) {
            year += 2000;
        } else {
            year += 1900;
        }
        return year;
    }

    public int getMonthFromPesel() {
        Pattern pattern = Pattern.compile("(\\d{2})(\\d{2})\\d{7}");
        Matcher matcher = pattern.matcher(this.pesel);
        matcher.matches();
        int month = Integer.parseInt(matcher.group(2));
        if (month > 20) month = month - 20;
        return month;
    }

    public int getDaysFromPesel() {
        Pattern pattern = Pattern.compile("(\\d{2})(\\d{2})(\\d{2})\\d{5}");
        Matcher matcher = pattern.matcher(this.pesel);
        matcher.matches();
        return Integer.parseInt(matcher.group(3));
    }*/
    public int getAge() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthDate);
        int birthYear = cal.get(Calendar.YEAR);
        int birthMonth = cal.get(Calendar.MONTH) + 1;
        int birthDay = cal.get(Calendar.DAY_OF_MONTH);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthYear;

        if (today.get(Calendar.MONTH) < birthMonth || (today.get(Calendar.MONTH) == birthMonth && today.get(Calendar.DAY_OF_MONTH) < birthDay)) {
            age--;
        }

        return age;
    }

   /* public int getAge() {
        Pattern patternYear = Pattern.compile("(\\d{2})(\\d{2})(\\d{2})\\d{5}");
        Matcher matcher = patternYear.matcher(this.pesel);
        matcher.matches();
        int year = Integer.parseInt(matcher.group(1));
        int month = Integer.parseInt(matcher.group(2));
        int day = Integer.parseInt(matcher.group(3));
        if (month > 20) {
            year += 2000;
            month -= 20;
        } else {
            year += 1900;
        }
        String birthdayString = year + "-" + String.format("%02d", month) + "-" + String.format("%02d", day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse(birthdayString, formatter);
        Period ageCalculate = Period.between(birthday, LocalDate.now());

        return ageCalculate.getYears();
    }*/

    public static void printAllDoctors() {
        for (Doctor d : doctorList) {
            System.out.println(d.toString());
        }
    }

    public static List<Doctor> getDoctorList() {
        return doctorList;
    }

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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public static Doctor getDoctorById(int doctorsId) {
        Doctor requestedDoctor = null;
        for (Doctor d : Doctor.getDoctorList()) {
            requestedDoctor = (d.getId() == doctorsId) ? d : requestedDoctor;
        }
        return requestedDoctor;
    }

    public static void setDoctorList(List<Doctor> doctorList) {
        Doctor.doctorList = doctorList;
    }

    public void addVisit(Visit visit) {
        visits.add(visit);
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return "Doctor:: " +
                "id: " + id +
                ", lastName: '" + lastName + '\'' +
                ", firstName: '" + firstName + '\'' +
                ", specialty: '" + specialty + '\'' +
                ", birthDate: " + birthDate +
                ", nip: '" + nip + '\'' +
                ", pesel: '" + pesel + '\'';
    }
}