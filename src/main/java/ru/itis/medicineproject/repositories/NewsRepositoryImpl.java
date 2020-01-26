package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.lib.DbConnection;
import ru.itis.medicineproject.model.News;
import ru.itis.medicineproject.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsRepositoryImpl implements NewsRepository {

    private DbConnection dbConnection;

    public NewsRepositoryImpl() {
        dbConnection = new DbConnection();
    }

    private RowMapper<News> rowMapper = rs -> {
        try {
            return News.builder()
                    .setTitle(rs.getString("title"))
                    .setAuthorId(rs.getLong("author_id"))
                    .setCreatedAtTime(rs.getDate("created_at_time"))
                    .setImagePath(rs.getString("image_path"))
                    .setText(rs.getString("text"))
                    .setPreviewText(rs.getString("preview_text"))
                    .setId(rs.getLong("id"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    };

    @Override
    public Optional<News> save(News model) {
        try(PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("INSERT INTO medicine.news(title, text, author_id, image_path, preview_text) " +
                "VALUE (?,?,?,?, ?)")) {
            preparedStatement.setString(1, model.getTitle());
            preparedStatement.setString(2, model.getText());
            preparedStatement.setLong(3, model.getAuthorId());
            preparedStatement.setString(4, model.getImagePath());
            preparedStatement.setString(5, model.getPreviewText());
            preparedStatement.executeUpdate();
            return Optional.of(model);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<News> find(News model) {
        try(PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM news WHERE id = ?")){
            preparedStatement.setLong(1, model.getId());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return Optional.of(rowMapper.mapRow(rs));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<News> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void update(News model) {

    }

    @Override
    public void delete(News model) {

    }

    @Override
    public List<News> findAll() {
        List<News> news = new ArrayList<>();
        try(PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM news WHERE created_at_time < now() ORDER BY created_at_time DESC")) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                news.add(rowMapper.mapRow(rs));
            }
            return news;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<News> findFirstThree() {
        List<News> news = new ArrayList<>();
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM news WHERE created_at_time < now() ORDER BY created_at_time DESC LIMIT 0, 3")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                news.add(rowMapper.mapRow(resultSet));
            }
            return news;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
