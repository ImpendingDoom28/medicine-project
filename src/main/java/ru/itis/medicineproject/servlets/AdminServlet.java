package ru.itis.medicineproject.servlets;

import ru.itis.medicineproject.context.PrimitiveContext;
import ru.itis.medicineproject.lib.Bean;
import ru.itis.medicineproject.lib.FreemarkerConfig;
import ru.itis.medicineproject.model.Disease;
import ru.itis.medicineproject.services.DiseaseService;
import ru.itis.medicineproject.services.FileUploadService;
import ru.itis.medicineproject.services.NewsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 50,
        maxRequestSize = 1024 * 1024 * 100)
public class AdminServlet extends HttpServlet {


    private String templateName = "ftl/admin.ftl";
    private DiseaseService diseaseService;
    private NewsService newsService;
    private FileUploadService fileUploadService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("componentsContext");
        PrimitiveContext primitiveContext = (PrimitiveContext) rawAttribute;
        this.diseaseService = primitiveContext.diseaseService();
        this.newsService = primitiveContext.newsService();
        this.fileUploadService = primitiveContext.fileUploadService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        if (Bean.getInstance().getUserDto() != null) {
            root.put("bean", Bean.getInstance());
        }
        root.put("success", req.getSession(false).getAttribute("success"));
        root.put("errors", req.getSession(false).getAttribute("errors"));
        FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), templateName);
        req.getSession(false).setAttribute("success", null);
        req.getSession(false).setAttribute("errors", null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = fileUploadService.downloadFile(req);
        if (!errors.isEmpty()) {
            req.getSession(false).setAttribute("errors", errors);
        } else {
            req.getSession(false).setAttribute("success", "Вы успешно добавили" + (req.getPart("news_image") == null ? " болезнь" : " новость") + "!");
        }
        resp.sendRedirect("/admin");
    }

}
