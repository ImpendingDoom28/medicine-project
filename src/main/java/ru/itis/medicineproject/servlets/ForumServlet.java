package ru.itis.medicineproject.servlets;

import ru.itis.medicineproject.context.PrimitiveContext;
import ru.itis.medicineproject.lib.Bean;
import ru.itis.medicineproject.lib.FreemarkerConfig;
import ru.itis.medicineproject.services.MessageService;
import ru.itis.medicineproject.services.TopicService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ForumServlet extends HttpServlet {

    private String forumTemplateName = "ftl/forum.ftl";
    private String topicTemplateName = "ftl/topic.ftl";
    private TopicService topicService;
    private MessageService messageService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("componentsContext");
        PrimitiveContext primitiveContext = (PrimitiveContext)rawAttribute;
        this.topicService = primitiveContext.topicService();
        this.messageService = primitiveContext.messageService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        System.out.println("URI -> " + req.getRequestURI());
        if(Bean.getInstance().getUserDto() != null) {
            root.put("bean", Bean.getInstance());
        }
        root.put("success", req.getSession(false).getAttribute("success"));
        root.put("errors", req.getSession(false).getAttribute("errors"));
        if(req.getRequestURI().startsWith("/forum/id")) {
            if(topicService.findTopicById(Long.parseLong(req.getRequestURI().substring(9))).isPresent()) {
                root.put("topic", topicService.findTopicById(Long.parseLong(req.getRequestURI().substring(9))).get());
                FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), topicTemplateName);
            }
        } else {
            root.put("topics", topicService.findAllTopics());
            FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), forumTemplateName);
        }
        req.getSession(false).setAttribute("success", null);
        req.getSession(false).setAttribute("errors", null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getRequestURI().equals("/forum/create")) {
            String name = req.getParameter("name");
            List<String> errors = topicService.addTopic(name, Bean.getInstance().getUserDto().getId());
            if(errors.isEmpty()) {
                resp.sendRedirect("/forum/id" + topicService.findTopicByName(name).get().getId());
            } else {
                req.getSession(false).setAttribute("errors", errors);
                resp.sendRedirect("/forum");
            }
        } else if (req.getRequestURI().startsWith("/forum/id")) {
            String text = req.getParameter("text");
            messageService.addMessage(text, Bean.getInstance().getUserDto().getId(), Long.parseLong(req.getRequestURI().substring(9)));
            doGet(req, resp);
        }
    }

}
