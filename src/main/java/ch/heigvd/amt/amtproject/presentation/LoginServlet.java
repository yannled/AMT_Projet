package ch.heigvd.amt.amtproject.presentation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/user")
public class LoginServlet extends javax.servlet.http.HttpServlet {


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    //source : https://stackoverflow.com/questions/31410007/how-to-do-pagination-in-jsp
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        //request.getRequestDispatcher("/WEB-INF/pages/view.jsp?page="+page.getCurrentPage()).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        
        //request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
