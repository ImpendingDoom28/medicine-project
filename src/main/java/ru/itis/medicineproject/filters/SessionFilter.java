package ru.itis.medicineproject.filters;

import ru.itis.medicineproject.context.PrimitiveContext;
import ru.itis.medicineproject.dto.UserDto;
import ru.itis.medicineproject.lib.Bean;
import ru.itis.medicineproject.model.User;
import ru.itis.medicineproject.repositories.RoleRepository;
import ru.itis.medicineproject.repositories.UserRepository;
import ru.itis.medicineproject.repositories.UserRepositoryImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;

@WebFilter(filterName = "SessionFilter")
public class SessionFilter implements Filter {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        Object rawAttribute = servletContext.getAttribute("componentsContext");
        PrimitiveContext primitiveContext = (PrimitiveContext)rawAttribute;
        this.userRepository = primitiveContext.userRepository();
        this.roleRepository = primitiveContext.roleRepository();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("===IN SESSION FILTER [" + Calendar.getInstance().getTime() + "]===");
        String emailCookie = findCookie((HttpServletRequest)servletRequest);
        System.out.println("check session --> " + checkSession((HttpServletRequest)servletRequest));
        System.out.println("check cookie --> " + (emailCookie != null));
        if(checkSession(((HttpServletRequest) servletRequest))) {
            System.out.println("Configuring bean with session...");
            configureBean((String)((HttpServletRequest)servletRequest).getSession().getAttribute("email"), (HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse);
        } else if (emailCookie != null) {
            System.out.println("Configuring bean with cookie...");
            configureBean(emailCookie, (HttpServletRequest)servletRequest, (HttpServletResponse)servletResponse);
        } else {
            String uri = ((HttpServletRequest)servletRequest).getRequestURI();
            if (uri.equals("/profile")) {
                ((HttpServletResponse) servletResponse).sendRedirect("/");
            }
            Bean.getInstance().invalidate();
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void configureBean(String email, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        System.out.println("Uri --> " + uri);
        System.out.println("Email --> " + email);
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user;
        System.out.println("User with given email is found in db --> " + userOptional.isPresent());
        if(userOptional.isPresent()) {
            System.out.println("Setting founded user to Bean...");
            user = User.builder()
                    .setRole(roleRepository.findRolesByUserId(userOptional.get().getId()).get())
                    .setSurname(userOptional.get().getSurname())
                    .setName(userOptional.get().getName())
                    .setEmail(userOptional.get().getEmail())
                    .setId(userOptional.get().getId())
                    .build();
            System.out.println("Our user:");
            System.out.println(user.toString());
            Bean.getInstance().setUserDto(UserDto.from(user));
            if (uri.equals("/login") || uri.equals("/register")) {
                // Когда пользователь заходит на страничку /login и /register
                // Кидаем его в профиль
                response.sendRedirect("/profile/id" + Bean.getInstance().getUserDto().getId());
            }
        }
        System.out.println("Finished configuring!");
        System.out.println();
    }

    private boolean checkSession(HttpServletRequest request) {
        if(request.getSession() != null) {
            return request.getSession().getAttribute("isOk") != null;
        } else {
            return false;
        }
    }

    private String findCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String cookieNameToLookFor = "email";
        if(cookies != null) {
            for (Cookie cookie: cookies) {
                if(cookie.getName().equals(cookieNameToLookFor)) {
                    System.out.println(cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void destroy() {

    }
}
