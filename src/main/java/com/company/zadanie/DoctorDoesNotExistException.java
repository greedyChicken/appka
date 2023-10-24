package com.company.zadanie;

public class DoctorDoesNotExistException extends RuntimeException{
    public DoctorDoesNotExistException(String message) {
        super(message);
    }
}
