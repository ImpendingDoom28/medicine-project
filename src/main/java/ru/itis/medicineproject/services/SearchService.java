package ru.itis.medicineproject.services;

import org.json.JSONObject;
import ru.itis.medicineproject.model.Disease;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SearchService {
    List<Disease> getSearchedDiseases(HttpServletRequest req);

    JSONObject getResponseAsJson(List<Disease> foundedDiseases);
}
