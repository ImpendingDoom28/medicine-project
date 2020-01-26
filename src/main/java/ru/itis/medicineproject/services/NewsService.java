package ru.itis.medicineproject.services;

import ru.itis.medicineproject.model.News;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    List<String> addNews(String title, String text, String imagePath, Long id);

    Optional<News> findNewById(Long id);

    List<News> findAllNews();

    List<News> findFirstThreeNews();
}
