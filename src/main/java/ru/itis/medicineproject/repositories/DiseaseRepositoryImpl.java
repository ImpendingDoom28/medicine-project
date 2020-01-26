package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.lib.DbConnection;
import ru.itis.medicineproject.model.Disease;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiseaseRepositoryImpl implements DiseaseRepository {

    private DbConnection dbConnection;

    public DiseaseRepositoryImpl() {
        dbConnection = new DbConnection();
    }

    private RowMapper<Disease> rowMapper = rs -> {
        try {
            return Disease.builder()
                    .setAuthorId(rs.getLong("author_id"))
                    .setCreatedAtTime(rs.getDate("created_at_time"))
                    .setImagePath(rs.getString("image_path"))
                    .setDescription(rs.getString("description"))
                    .setName(rs.getString("name"))
                    .setId(rs.getLong("id"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    };

    @Override
    public Optional<Disease> save(Disease model) {
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("INSERT INTO disease(name, description, author_id, image_path) VALUE (?, ?, ?, ?)")) {
            preparedStatement.setString(1, model.getName());
            preparedStatement.setString(2, model.getDescription());
            preparedStatement.setLong(3, model.getAuthorId());
            preparedStatement.setString(4, model.getImagePath());
            preparedStatement.executeUpdate();
            return Optional.of(model);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Disease> find(Disease model) {
        return Optional.empty();
    }

    @Override
    public Optional<Disease> findById(Long aLong) {
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM disease WHERE id = ?")) {
            preparedStatement.setLong(1, aLong);
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
    public void update(Disease model) {

    }

    @Override
    public void delete(Disease model) {

    }

    @Override
    public List<Disease> findAll() {
        List<Disease> diseases = new ArrayList<>();
        try(PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM disease")) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                diseases.add(rowMapper.mapRow(rs));
            }
            return diseases;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Disease> findDiseasesByName(String nameToSearch) {
        List<Disease> diseases = new ArrayList<>();
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM disease WHERE name LIKE ?")) {
            preparedStatement.setString(1, "%" + nameToSearch + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                diseases.add(rowMapper.mapRow(rs));
            }
            return diseases;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
