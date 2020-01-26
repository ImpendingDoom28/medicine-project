package ru.itis.medicineproject.services;

import java.util.List;

public interface SignUpService {

    List<String> signUp(String email, String password, String name, String surname);

    boolean validateEmail(String email);

    List<String> validatePassword(String password);
}
