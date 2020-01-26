package ru.itis.medicineproject.servlets;

import ru.itis.medicineproject.context.PrimitiveContext;
import ru.itis.medicineproject.lib.Bean;
import ru.itis.medicineproject.lib.FreemarkerConfig;
import ru.itis.medicineproject.services.DiseaseService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DiseasesServlet extends HttpServlet {

    private String allTemplateName = "ftl/diseases.ftl";
    private String diseaseTemplateName = "ftl/disease-info.ftl";
    private DiseaseService diseaseService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("componentsContext");
        PrimitiveContext primitiveContext = (PrimitiveContext)rawAttribute;
        this.diseaseService = primitiveContext.diseaseService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        if (Bean.getInstance().getUserDto() != null) {
            root.put("bean", Bean.getInstance());
        }
        if (req.getRequestURI().startsWith("/diseases/id")) {
            root.put("disease", diseaseService.findDiseaseById(Long.parseLong(req.getRequestURI().substring(12))).get());
            FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), diseaseTemplateName);
        } else {
            root.put("diseases", diseaseService.findAllDiseases());
            FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), allTemplateName);
        }
    }

}
