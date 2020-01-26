package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.model.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicRepository extends CrudRepository<Topic, Long> {
    List<Topic> findAllTopics();

    Optional<Topic> findByName(String name);
}
