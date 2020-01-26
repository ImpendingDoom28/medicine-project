package ru.itis.medicineproject.services;

import java.util.List;

public interface SignInService {

    List<String> signIn(String email, String password);

}
