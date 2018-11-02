package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;

import javax.ejb.EJB;
import ch.heigvd.amt.amtproject.model.Error;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {

    public static String VUE = "/WEB-INF/pages/login.jsp";
    public static String PROJECTS = "/WEB-INF/pages/projects.jsp";

    @EJB
    private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        List<Error> errors = new ArrayList<>();
        Error errorPassword = new Error();
        Error errorEmail = new Error();

        boolean syntaxOK = true;

        if (!email.contains("@")) {
            errorEmail.setErrorText( "bad email structure !");
            errorEmail.setError(true);
            syntaxOK = false;
        }
        else {
            errorEmail.setValue(email);
        }

        if (password.equals("")){
            errorPassword.setErrorText("password empty !");
            errorPassword.setError(true);
            syntaxOK = false;
        }

        if (syntaxOK) {
            //request.getRequestDispatcher("projects");
            //return;
            //TODO : Faire les tests si dessous avec la base de donnée

            if(userDAO.isValid(email, password)) {
                request.getRequestDispatcher(PROJECTS).forward(request, response);
                return;
            }
            else{
                errorPassword.setErrorText("wrong password");
                errorPassword.setError(true);
                // TODO correct message, should be more explicit if user not exits -> bad email or wrong password
                errorEmail.setErrorText( "email not found");
                errorEmail.setError(true);
            }

        }

        errors.add(errorEmail);
        errors.add(errorPassword);

        request.setAttribute("errors", errors);
        request.getRequestDispatcher(VUE).forward(request, response);
    }
}
