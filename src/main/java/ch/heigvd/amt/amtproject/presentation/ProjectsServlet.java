package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet("/projects")
public class ProjectsServlet extends javax.servlet.http.HttpServlet {

    public static String VUE = "/WEB-INF/pages/projects.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VUE).forward(request, response);
    }
}
