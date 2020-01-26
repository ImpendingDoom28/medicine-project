package ru.itis.medicineproject.listeners;

import ru.itis.medicineproject.context.PrimitiveContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class PrimitiveContexServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("IN LISTENER");
        ServletContext servletContext = servletContextEvent.getServletContext();
        PrimitiveContext primitiveContext = new PrimitiveContext();
        servletContext.setAttribute("componentsContext", primitiveContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
