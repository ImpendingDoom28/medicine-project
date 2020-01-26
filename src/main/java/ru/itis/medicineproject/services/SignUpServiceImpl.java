package ru.itis.medicineproject.services;

import ru.itis.medicineproject.model.User;
import ru.itis.medicineproject.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpServiceImpl implements SignUpService{

    private SecurityService securityService;
    private UserRepository userRepository;

    public SignUpServiceImpl(UserRepository userRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    @Override
    public List<String> signUp(String email, String password, String name, String surname) {
        List<String> errors = new ArrayList<>();
        if(!validateEmail(email)) {
            errors.add("Неверно введён e-mail, пожалуйста проверьте написание!");
        }
        List<String> passErrors = validatePassword(password);
        if(!passErrors.isEmpty()) {
            errors.addAll(passErrors);
        }
        if(userRepository.findByEmail(email).isPresent()) {
            errors.add("Пользователь с таким e-mail'ом уже существует!");
        }
        if(errors.isEmpty()) {
                userRepository.save(User.builder()
                        .setEmail(email)
                        .setPassword(securityService.hashPassword(password))
                        .setName(name)
                        .setSurname(surname)
                        .build());
                return null;
            } else {
                return errors;
        }
    }

    @Override
    public boolean validateEmail(String email) {
        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                        "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public List<String> validatePassword(String password) {
        List<String> errors = new ArrayList<>();
        if(password.length() < 6) {
            errors.add("Ваш пароль слишком короткий! (Минимум 6 символов)");
        }
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(password);
        if(!matcher.find()) {
            errors.add("Пароль должен содержать хотя бы 1 заглавную букву!");
        }
        return errors;
    }
}