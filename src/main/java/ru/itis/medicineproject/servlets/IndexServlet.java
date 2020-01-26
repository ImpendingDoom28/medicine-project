package ru.itis.medicineproject.servlets;

import ru.itis.medicineproject.context.PrimitiveContext;
import ru.itis.medicineproject.lib.Bean;
import ru.itis.medicineproject.lib.FreemarkerConfig;
import ru.itis.medicineproject.services.NewsService;
import ru.itis.medicineproject.services.NewsServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {

    private String templateName = "ftl/index.ftl";
    private NewsService newsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("componentsContext");
        PrimitiveContext primitiveContext = (PrimitiveContext)rawAttribute;
        this.newsService = primitiveContext.newsService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        if(Bean.getInstance().getUserDto() != null) {
            root.put("bean", Bean.getInstance());
        }
        root.put("news", newsService.findFirstThreeNews());
        root.put("otherNews", newsService.findAllNews());
        FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), templateName);
    }

}
