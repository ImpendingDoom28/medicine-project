package ru.itis.medicineproject.servlets;

import ru.itis.medicineproject.context.PrimitiveContext;
import ru.itis.medicineproject.lib.Bean;
import ru.itis.medicineproject.lib.FreemarkerConfig;
import ru.itis.medicineproject.services.NewsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FeedServlet extends HttpServlet {

    private String allTemplateName = "ftl/feed.ftl";
    private String newsTemplateName = "ftl/feed-info.ftl";

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        if(Bean.getInstance().getUserDto() != null) {
            root.put("bean", Bean.getInstance());
        }
        if(req.getRequestURI().startsWith("/feed/id")) {
            root.put("new", newsService.findNewById(Long.parseLong(req.getRequestURI().substring(8))).get());
            FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(),newsTemplateName);
        } else if(req.getRequestURI().equals("/feed")) {
            root.put("news", newsService.findAllNews());
            FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), allTemplateName);
        }
    }

}
