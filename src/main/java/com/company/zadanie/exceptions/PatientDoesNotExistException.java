package com.company.zadanie.exceptions;

public class PatientDoesNotExistException extends RuntimeException{
    public PatientDoesNotExistException(String message) {
        super(message);
    }
}
