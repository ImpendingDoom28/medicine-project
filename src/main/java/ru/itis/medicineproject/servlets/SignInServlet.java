package ru.itis.medicineproject.servlets;

import ru.itis.medicineproject.context.PrimitiveContext;
import ru.itis.medicineproject.lib.FreemarkerConfig;
import ru.itis.medicineproject.repositories.UserRepository;
import ru.itis.medicineproject.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "SignInServlet")
public class SignInServlet extends HttpServlet {

    private String templateName = "ftl/login.ftl";
    private SignInService signInService;
    private UserRepository userRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        Object rawAttribute = servletContext.getAttribute("componentsContext");
        PrimitiveContext primitiveContext = (PrimitiveContext)rawAttribute;
        this.signInService = primitiveContext.signInService();
        this.userRepository = primitiveContext.userRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        System.out.println("Email: " + email);
        String password = req.getParameter("password");
        System.out.println("Password: " + password);
        List<String> errors = signInService.signIn(email, password);
        if(errors.isEmpty()) {
            String rememberMe = req.getParameter("rememberMe");
            if(rememberMe != null) {
                Cookie cookie = new Cookie("email", email);
                cookie.setMaxAge(43200 * 60);
                resp.addCookie(cookie);
            }
            HttpSession session = req.getSession(true);
            session.setAttribute("isOk", true);
            session.setAttribute("email", email);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("errors", errors);
            doGet(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> root = new HashMap<>();
        root.put("success", req.getSession(false).getAttribute("success"));
        root.put("errors", req.getAttribute("errors"));
        FreemarkerConfig.preprocessConfig(resp.getWriter(), root, getServletContext(), templateName);
        req.getSession().removeAttribute("success");
        req.setAttribute("errors", null);
    }

}
