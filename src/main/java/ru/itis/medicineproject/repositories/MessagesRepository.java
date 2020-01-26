package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessagesRepository extends CrudRepository<Message, Long>{
    List<Message> findAllMessagesForTopicById(Long id);

    void addToMtm(Long topicId, Long messageId);

    Optional<Message> findByText(String text);
}
