package ru.itis.medicineproject.services;

import ru.itis.medicineproject.model.Message;
import ru.itis.medicineproject.model.Topic;
import ru.itis.medicineproject.repositories.MessagesRepository;
import ru.itis.medicineproject.repositories.TopicRepository;
import ru.itis.medicineproject.repositories.UserRepository;

import java.util.Optional;

public class MessageServiceImpl implements MessageService {

    private MessagesRepository messagesRepository;
    private UserRepository userRepository;
    private TopicRepository topicRepository;

    public MessageServiceImpl(MessagesRepository messagesRepository, UserRepository userRepository, TopicRepository topicRepository) {
        this.messagesRepository = messagesRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public void addMessage(String text, Long userId, Long topicId) {
        Optional<Message> messageOptional = messagesRepository.save(Message.builder().setAuthor(userRepository.findById(userId).get()).setText(text).build());
        // Добавляем что это последнее сообщение
        messagesRepository.addToMtm(topicId, messageOptional.get().getId());
        topicRepository.update(Topic.builder().setId(topicId).setLastMessage(messageOptional.get()).build());
    }
}
