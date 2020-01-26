package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.lib.DbConnection;
import ru.itis.medicineproject.model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {

    private DbConnection dbConnection;

    public RoleRepositoryImpl() {
        dbConnection = new DbConnection();
    }

    private RowMapper<Role> rowMapper = rs -> {
        try {
            return Role.builder()
                    .setName(rs.getString("name"))
                    .setUserId(rs.getLong("user_id"))
                    .build();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    };

    @Override
    public Optional<List<Role>> findRolesByUserId(Long id) {
        List<Role> roles = new ArrayList<>();
        try(PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement("SELECT medicine.user_to_role.user_id, medicine.role.name FROM medicine.user_to_role INNER JOIN role ON medicine.user_to_role.role_id = medicine.role.id WHERE user_id = ?")) {
            preparedStatement.setString(1, "" + id);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                roles.add(rowMapper.mapRow(rs));
            }
            return Optional.of(roles);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<Role> save(Role model) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> find(Role model) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public void update(Role model) {

    }

    @Override
    public void delete(Role model) {

    }
}
