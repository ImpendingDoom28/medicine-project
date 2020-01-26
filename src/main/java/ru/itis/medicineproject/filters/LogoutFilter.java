package ru.itis.medicineproject.filters;

import ru.itis.medicineproject.lib.Bean;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogoutFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(Bean.getInstance().getUserDto() != null) {
            ((HttpServletRequest)servletRequest).getSession(false).invalidate();
            Cookie cookie = new Cookie("email", "");
            cookie.setMaxAge(0);
            ((HttpServletResponse)servletResponse).addCookie(cookie);
            ((HttpServletRequest)servletRequest).getSession().setAttribute("success", "Вы успешно вышли из профиля!");
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            List<String> errors = new ArrayList<>();
            errors.add("Вы не можете выйти из аккаунта, т.к. вы не вошли в него");
            ((HttpServletRequest)servletRequest).getSession().setAttribute("errors", errors);
            ((HttpServletResponse)servletResponse).sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}
