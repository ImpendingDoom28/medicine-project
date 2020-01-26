package ru.itis.medicineproject.services;

import ru.itis.medicineproject.model.News;
import ru.itis.medicineproject.repositories.NewsRepository;

import java.util.List;
import java.util.Optional;

public class NewsServiceImpl implements NewsService{

    private NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public List<String> addNews(String title, String text, String imagePath, Long id) {
        return null;
    }

    @Override
    public Optional<News> findNewById(Long id) {
        return newsRepository.find(News.builder().setId(id).build());
    }

    @Override
    public List<News> findAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> findFirstThreeNews() {
        return newsRepository.findFirstThree();
    }
}
