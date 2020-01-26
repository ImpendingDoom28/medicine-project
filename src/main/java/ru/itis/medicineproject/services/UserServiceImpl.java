package ru.itis.medicineproject.services;

import ru.itis.medicineproject.model.User;
import ru.itis.medicineproject.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> changeUserInfo(String email, String name, String surname) {
        List<String> errors = new ArrayList<>();
        if(name.equals("") && surname.equals("")) {
            errors.add("Вы не ввели ни имя, ни фамилию! Исправление невозможно");
        } else {
            userRepository.update(User.builder()
                    .setEmail(email)
                    .setName(name.equals("") ? null : name)
                    .setSurname(surname.equals("") ? null : surname)
                    .build());
        }
        return errors;
    }
}
