package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.model.News;

import java.util.List;

public interface NewsRepository extends CrudRepository<News, Long> {

    List<News> findAll();

    List<News> findFirstThree();
}
