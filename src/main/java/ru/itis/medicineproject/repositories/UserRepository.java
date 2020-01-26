package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>  {

    Optional<User> findByEmail(String email);
}
