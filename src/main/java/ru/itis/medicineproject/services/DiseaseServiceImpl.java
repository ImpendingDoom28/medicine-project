package ru.itis.medicineproject.services;

import ru.itis.medicineproject.model.Disease;
import ru.itis.medicineproject.repositories.DiseaseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiseaseServiceImpl implements DiseaseService {

    private DiseaseRepository diseaseRepository;

    public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public Optional<Disease> findDiseaseByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Disease> findAllDiseases() {
        return diseaseRepository.findAll();
    }

    @Override
    public Optional<Disease> findDiseaseById(Long id) {
        return diseaseRepository.findById(id);
    }

    @Override
    public List<String> addDisease(String name, String description, String imagePath, Long id) {
        List<String> errors = new ArrayList<>();
        if(diseaseRepository.find(Disease.builder().setName(name).build()).isPresent()) {
            errors.add("Болезнь с таким названием уже существует!!!");
        }
        if(errors.isEmpty()) {
            diseaseRepository.save(Disease.builder()
                    .setName(name)
                    .setAuthorId(id)
                    .setDescription(description)
                    .setImagePath(imagePath)
                    .build());
        }
        return errors;
    }
}
