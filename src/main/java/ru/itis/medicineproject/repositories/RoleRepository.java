package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<List<Role>> findRolesByUserId(Long id);
}
