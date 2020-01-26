package ru.itis.medicineproject.services;

import java.util.List;

public interface UserService {

    List<String> changeUserInfo(String email, String name, String surname);
}
