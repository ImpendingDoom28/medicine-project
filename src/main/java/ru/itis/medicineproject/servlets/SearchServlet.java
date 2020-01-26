package ru.itis.medicineproject.servlets;

import org.json.JSONObject;
import ru.itis.medicineproject.context.PrimitiveContext;
import ru.itis.medicineproject.model.Disease;
import ru.itis.medicineproject.model.News;
import ru.itis.medicineproject.services.SearchService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {

    private SearchService searchService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("componentsContext");
        PrimitiveContext primitiveContext = (PrimitiveContext) rawAttribute;
        this.searchService = primitiveContext.searchService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Disease> foundedDiseases = searchService.getSearchedDiseases(req);
        JSONObject jsonObject = searchService.getResponseAsJson(foundedDiseases);
        resp.setContentType("text/json");
        resp.getWriter().write(jsonObject.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
