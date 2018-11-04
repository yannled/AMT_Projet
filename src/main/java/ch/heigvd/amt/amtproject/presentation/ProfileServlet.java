package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.UserDAO;
import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlet extends javax.servlet.http.HttpServlet {

    public static String PROFILE = "/WEB-INF/pages/profile.jsp";

    @EJB
    private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        //TODO get session user id
        long currentUserId = 7;

        //TODO Get the current user
        if(request.getParameter("modify") != null){
            request.setAttribute("modify", true);
        }else{
            request.setAttribute("modify", false);
        }

        User user = userDAO.findById(currentUserId);

        request.setAttribute("currentUser", user);
        request.getRequestDispatcher(PROFILE).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        //TODO get session user id
        long currentUserId = 7;

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");


        userDAO.updateName(currentUserId, firstName, lastName);
        userDAO.updateEmail(currentUserId, email);

        doGet(request, response);
    }
}
