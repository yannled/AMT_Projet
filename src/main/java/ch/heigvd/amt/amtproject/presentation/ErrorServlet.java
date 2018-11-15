package ch.heigvd.amt.amtproject.presentation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/error")
public class ErrorServlet extends javax.servlet.http.HttpServlet {


    public static String ERROR = "/WEB-INF/pages/ErrorPage.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ERROR).forward(request, response);
    }
}