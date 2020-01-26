package ru.itis.medicineproject.services;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.itis.medicineproject.model.Disease;
import ru.itis.medicineproject.repositories.DiseaseRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class SearchServiceImpl implements SearchService {

    private DiseaseRepository diseaseRepository;

    public SearchServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public List<Disease> getSearchedDiseases(HttpServletRequest req) {
        String nameToSearch = req.getParameter("query");
        return diseaseRepository.findDiseasesByName(nameToSearch);
    }

    @Override
    public JSONObject getResponseAsJson(List<Disease> foundedDiseases) {
        JSONArray ja = new JSONArray();
        for (Disease disease : foundedDiseases) {
            ja.put(new JSONObject(disease));
        }
        JSONObject jo = new JSONObject();
        jo.put("objects", ja);
        return jo;
    }
}
