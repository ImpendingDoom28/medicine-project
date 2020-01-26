package ru.itis.medicineproject.services;

import ru.itis.medicineproject.model.Disease;
import ru.itis.medicineproject.model.Topic;

import java.util.List;
import java.util.Optional;

public interface DiseaseService {

    Optional<Disease> findDiseaseByName(String name);

    List<Disease> findAllDiseases();

    Optional<Disease> findDiseaseById(Long id);

    List<String> addDisease(String name, String text, String imagePath, Long id);
}
