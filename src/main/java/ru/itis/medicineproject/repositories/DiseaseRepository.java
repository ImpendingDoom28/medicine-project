package ru.itis.medicineproject.repositories;

import ru.itis.medicineproject.model.Disease;

import java.util.List;

public interface DiseaseRepository extends CrudRepository<Disease, Long>{
    List<Disease> findAll();

    List<Disease> findDiseasesByName(String nameToSearch);
}
