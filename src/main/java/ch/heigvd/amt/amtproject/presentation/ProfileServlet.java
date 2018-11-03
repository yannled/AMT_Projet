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
    private List<User> users;

    @EJB
    private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        users = userDAO.findAll();
        //TODO Get the current user
        if(request.getParameter("modify") != null){
            request.setAttribute("modify", true);
        }else{
            request.setAttribute("modify", false);
        }
        request.setAttribute("currentUser", users.get(0));
        request.setAttribute("users", users);
        request.getRequestDispatcher(PROFILE).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");



        userDAO.update();

        doGet(request, response);
    }
}
