package ru.itis.medicineproject.services;

import ru.itis.medicineproject.model.User;
import ru.itis.medicineproject.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    private UserRepository userRepository;
    private SecurityService securityService;

    public SignInServiceImpl(UserRepository userRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    @Override
    public List<String> signIn(String email, String password) {
        List<String> errors = new ArrayList<>();
        System.out.println(email + " " + password);
        Optional<User> optional = userRepository.findByEmail(email);
        if(!optional.isPresent()) {
            errors.add("Неверный e-mail");
        } else {
            if(!securityService.verifyPassword(password, optional.get().getPassword())) {
                errors.add("Неверный пароль");
            }
        }
        return errors;
    }
}
