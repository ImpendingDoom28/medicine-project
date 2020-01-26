package ru.itis.medicineproject.services;

import ru.itis.medicineproject.model.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {

    List<String> addTopic(String name, Long authorId);

    void deleteTopic(String name);

    Optional<Topic> findTopicByName(String name);

    List<Topic> findAllTopics();

    Optional<Topic> findTopicById(Long id);

}
