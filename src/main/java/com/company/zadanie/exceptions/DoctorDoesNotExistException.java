package com.company.zadanie.exceptions;

public class DoctorDoesNotExistException extends RuntimeException{
    public DoctorDoesNotExistException(String message) {
        super(message);
    }
}
