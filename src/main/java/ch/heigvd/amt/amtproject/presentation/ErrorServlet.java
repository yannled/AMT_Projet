package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.model.User;
import ch.heigvd.amt.amtproject.model.VerifySession;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
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