package ru.itis.medicineproject.servlets;

import ru.itis.medicineproject.context.PrimitiveContext;
import ru.itis.medicineproject.lib.Bean;
import ru.itis.medicineproject.lib.FreemarkerConfig;
import ru.itis.medicineproject.services.UserService;

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

public class ProfileServlet extends HttpServlet {

    private String templateName = "ftl/profile.ftl";
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("componentsContext");
        PrimitiveContext primitiveContext = (PrimitiveContext)rawAttribute;
        this.userService = primitiveContext.userService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        if(req.getRequestURI().startsWith("/profile/id")) {
            root.put("bean", Bean.getInstance());
            root.put("success", req.getSession(false).getAttribute("success"));
            root.put("errors", req.getSession(false).getAttribute("errors"));
        } else {
            resp.sendRedirect("/");
        }
        FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), templateName);
        req.getSession(false).setAttribute("success", null);
        req.getSession(false).setAttribute("errors", null);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = Bean.getInstance().getUserDto().getEmail();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        System.out.println(name + " ... " + surname);
        List<String> errors = userService.changeUserInfo(email, name, surname);
        if(errors.isEmpty()) {
            req.getSession(false).setAttribute("success", "Вы успешно изменили свои данные!");
        } else {
            req.getSession(false).setAttribute("errors", errors);
        }
        resp.sendRedirect("/profile/id" + Bean.getInstance().getUserDto().getId());

    }


}
