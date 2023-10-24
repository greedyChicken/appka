package com.company.zadanie;

public class PatientDoesNotExistException extends RuntimeException{
    public PatientDoesNotExistException(String message) {
        super(message);
    }
}
