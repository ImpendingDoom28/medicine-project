package ru.itis.medicineproject.services;

public interface SecurityService {

    String hashPassword(String password);

    boolean verifyPassword(String givenPassword, String passwordFromDb);
}
