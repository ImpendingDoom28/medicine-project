package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.lib.DbConnection;
import ru.itis.medicineproject.model.Message;
import ru.itis.medicineproject.model.Topic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TopicRepositoryImpl implements TopicRepository {

    private DbConnection dbConnection;
    private MessagesRepository messagesRepository;

    public TopicRepositoryImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
        dbConnection = new DbConnection();
    }

    private RowMapper<Topic> rowMapper = rs -> {
        try {
            return Topic.builder()
                    .setName(rs.getString("name"))
                    .setAuthorId(rs.getLong("author_id"))
                    .setId(rs.getLong("id"))
                    .setMessagesCount(rs.getInt("messages_count"))
                    .setLastMessage(messagesRepository.findByText(rs.getString("last_message")).isPresent() ? messagesRepository.findByText(rs.getString("last_message")).get() : null)
                    .build();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    };

    @Override
    public Optional<Topic> save(Topic model) {
        try(PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("INSERT INTO medicine.topic (name, author_id) VALUE (?, ?)")) {
            preparedStatement.setString(1, model.getName());
            preparedStatement.setLong(2, model.getAuthorId());
            preparedStatement.executeUpdate();
            return Optional.of(model);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Topic> find(Topic model) {
        try(PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM medicine.topic WHERE name = ?")) {
            preparedStatement.setString(1, model.getName());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) return Optional.of(rowMapper.mapRow(rs));
            else return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Topic> findById(Long aLong) {
        try(PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM medicine.topic WHERE id = ?")) {
            preparedStatement.setLong(1, aLong);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) return Optional.of(rowMapper.mapRow(rs));
            else return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Topic model) {
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE medicine.topic SET topic.last_message = ? WHERE topic.id = ?")){
            preparedStatement.setString(1, model.getLastMessage().getText());
            preparedStatement.setLong(2, model.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(Topic model) {

    }

    @Override
    public List<Topic> findAllTopics() {
        try(PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT topic.id, topic.name, topic.author_id, topic.last_message, topic.messages_count FROM forum INNER JOIN topic ON forum.topic_id = topic.id")) {
            List<Topic> topics = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                Topic topic = rowMapper.mapRow(rs);
                List<Message> messages = messagesRepository.findAllMessagesForTopicById(topic.getId());
                Topic toAdd = Topic.builder()
                        .setName(topic.getName())
                        .setAuthorId(topic.getAuthorId())
                        .setId(topic.getId())
                        .setLastMessage(topic.getLastMessage())
                        .setMessages(messages)
                        .setMessagesCount(messages.size())
                        .build();
                topics.add(toAdd);
            }
            return topics;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Topic> findByName(String name) {
        return Optional.empty();
    }
}
