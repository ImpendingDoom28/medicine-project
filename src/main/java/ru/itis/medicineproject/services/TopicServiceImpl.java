package ru.itis.medicineproject.services;

import ru.itis.medicineproject.model.Message;
import ru.itis.medicineproject.model.Topic;
import ru.itis.medicineproject.repositories.MessagesRepository;
import ru.itis.medicineproject.repositories.TopicRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;
    private MessagesRepository messagesRepository;

    public TopicServiceImpl(TopicRepository topicRepository, MessagesRepository messagesRepository) {
        this.topicRepository = topicRepository;
        this.messagesRepository = messagesRepository;
    }

    @Override
    public List<String> addTopic(String name, Long authorId) {
        List<String> errors = new ArrayList<>();
        if(findTopicByName(name).isPresent()) {
            errors.add("Тема с таким именем уже существует!");
        }
        if(name.equals("")) {
            errors.add("Заголовок темы не может быть пустым!");
        }
        if(errors.isEmpty()) {
            topicRepository.save(Topic.builder()
                    .setName(name)
                    .setAuthorId(authorId)
                    .build());
        }
        return errors;
    }

    @Override
    public void deleteTopic(String name) {
        topicRepository.delete(Topic.builder()
                .setName(name)
                .build());
    }

    @Override
    public Optional<Topic> findTopicByName(String name) {
        return topicRepository.find(Topic.builder()
                .setName(name)
                .build());
    }

    @Override
    public List<Topic> findAllTopics() {
        return topicRepository.findAllTopics();
    }

    @Override
    public Optional<Topic> findTopicById(Long id) {
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if(optionalTopic.isPresent()) {
            Topic topic;
            List<Message> messages = messagesRepository.findAllMessagesForTopicById(optionalTopic.get().getId());
            topic = Topic.builder()
                    .setMessages(messages)
                    .setMessagesCount(messages.size())
                    .setName(optionalTopic.get().getName())
                    .setAuthorId(optionalTopic.get().getAuthorId())
                    .setLastMessage(optionalTopic.get().getLastMessage())
                    .setId(optionalTopic.get().getId())
                    .build();
            return Optional.of(topic);
        } else return Optional.empty();
    }
}
