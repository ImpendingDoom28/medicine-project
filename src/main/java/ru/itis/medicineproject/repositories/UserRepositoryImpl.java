package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.lib.DbConnection;
import ru.itis.medicineproject.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    private DbConnection dbConnection;
    private RoleRepository roleRepository;

    public UserRepositoryImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        dbConnection = new DbConnection();
    }

    private RowMapper<User> userRowMapper = rs -> {
        // FIXME: 19.01.2020 add correct userRowMapper
        try {
            return User.builder()
                    .setId(rs.getLong("id"))
                    .setEmail(rs.getString("email"))
                    .setPassword(rs.getString("password"))
                    .setName(rs.getString("name"))
                    .setSurname(rs.getString("surname"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalArgumentException("Column not found");
        }
    };


    @Override
    public Optional<User> findByEmail(String email) {
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM medicine.user WHERE email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return Optional.of(userRowMapper.mapRow(rs));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> save(User model) {
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("INSERT INTO medicine.user (email, password, name, surname) VALUE (?, ?, ?, ?)")){
            preparedStatement.setString(1, model.getEmail());
            preparedStatement.setString(2, model.getPassword());
            preparedStatement.setString(3, model.getName());
            preparedStatement.setString(4, model.getSurname());
            preparedStatement.executeUpdate();
            return Optional.of(model);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> find(User model) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Long aLong) {
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT * FROM user WHERE id = ?")){
            preparedStatement.setLong(1, aLong);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()) {
                return Optional.of(userRowMapper.mapRow(rs));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(User model) {
        try (PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("UPDATE medicine.user SET user.name = ?, user.surname = ? WHERE user.email = ?")){
            preparedStatement.setString(1, model.getName() == null ? findByEmail(model.getEmail()).get().getName() : model.getName());
            System.out.println(model.getName());
            preparedStatement.setString(2, model.getSurname() == null ? findByEmail(model.getEmail()).get().getSurname() : model.getSurname());
            preparedStatement.setString(3, model.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(User model) {

    }
}
