package ru.itis.medicineproject.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityServiceImpl implements SecurityService {

    @Override
    public String hashPassword(String password) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        StringBuilder stringBuilder = new StringBuilder();
        // Получаем из последовательности байт пароля последовательность байт его хэша
        byte[] message = messageDigest.digest(password.getBytes());
        // Собираем строку из шестнадцатеричного представления байтовой
        // последовательности хэша для хранения в базе данных
        for (byte b : message) {
            stringBuilder.append(String.format("%02x", b));
        }
        return stringBuilder.toString();

    }

    @Override
    public boolean verifyPassword(String givenPassword, String passwordFromDb) {
        return hashPassword(givenPassword).equals(passwordFromDb);
    }
}
