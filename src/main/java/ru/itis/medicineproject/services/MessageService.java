package ru.itis.medicineproject.services;

public interface MessageService {

    void addMessage(String text,  Long userId, Long topicId);
}
