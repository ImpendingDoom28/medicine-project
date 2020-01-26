package ru.itis.medicineproject.servlets;

import ru.itis.medicineproject.context.PrimitiveContext;
import ru.itis.medicineproject.lib.FreemarkerConfig;
import ru.itis.medicineproject.services.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SignUpServlet")
public class SignUpServlet extends HttpServlet {

    private String templateName = "ftl/register.ftl";
    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("componentsContext");
        PrimitiveContext primitiveContext = (PrimitiveContext)rawAttribute;
        this.signUpService = primitiveContext.signUpService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> errors = signUpService.signUp(req.getParameter("email"), req.getParameter("password"), req.getParameter("name"), req.getParameter("surname"));
        if(errors == null) {
            req.setAttribute("success", "Вы успешно зарегистрировались! Теперь вы можете авторизоваться");
            resp.sendRedirect("login");
        } else {
            req.setAttribute("errors", errors);
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("errors", req.getAttribute("errors"));
        FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), templateName);
        req.setAttribute("errors", null);
    }

}
