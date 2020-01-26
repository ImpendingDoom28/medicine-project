package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.lib.DbConnection;
import ru.itis.medicineproject.model.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessagesRepositoryImpl implements MessagesRepository {

    private DbConnection dbConnection;
    private UserRepository userRepository;

    public MessagesRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        dbConnection = new DbConnection();
    }

    private RowMapper<Message> rowMapper = rs -> {
        try {
            return Message.builder()
                    .setText(rs.getString("text"))
                    .setDatetime(rs.getDate("datetime"))
                    .setId(rs.getLong("id"))
                    .setAuthor(userRepository.findById(rs.getLong("author_id")).get())
                    .build();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    };

    @Override
    public Optional<Message> save(Message model) {
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("INSERT INTO message(text, author_id) VALUE (?, ?)")) {
            preparedStatement.setString(1, model.getText());
            preparedStatement.setLong(2, model.getAuthor().getId());
            preparedStatement.executeUpdate();
            return findByText(model.getText());
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Message> findByText(String text) {
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM message WHERE text = ?")) {
            preparedStatement.setString(1, text);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return Optional.of(rowMapper.mapRow(rs));
            } else return Optional.empty();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Message> find(Message model) {
        return Optional.empty();
    }

    @Override
    public Optional<Message> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void update(Message model) {

    }

    @Override
    public void delete(Message model) {

    }

    @Override
    public List<Message> findAllMessagesForTopicById(Long id) {
        List<Message> messages = new ArrayList<>();
        try(PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM medicine.topic_to_message INNER JOIN medicine.message ON topic_to_message.message_id = message.id WHERE topic_to_message.topic_id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                messages.add(rowMapper.mapRow(rs));
            }
            return messages;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void addToMtm(Long topicId, Long messageId) {
        try(PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("INSERT INTO medicine.topic_to_message(topic_id, message_id) VALUE (?, ?)")) {
            preparedStatement.setLong(1, topicId);
            preparedStatement.setLong(2, messageId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
