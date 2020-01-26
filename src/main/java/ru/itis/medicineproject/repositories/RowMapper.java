package ru.itis.medicineproject.repositories;

import java.sql.ResultSet;

public interface RowMapper<T> {

    T mapRow(ResultSet rs);
}
