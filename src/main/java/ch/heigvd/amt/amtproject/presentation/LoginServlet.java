package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;

import javax.ejb.EJB;
import ch.heigvd.amt.amtproject.model.Error;
import ch.heigvd.amt.amtproject.model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/login")
public class LoginServlet extends javax.servlet.http.HttpServlet {

    public static String LOGIN = "/WEB-INF/pages/login.jsp";
    public static String HOME = "/WEB-INF/pages/home.jsp";
    public static String PSWCHANGE = "/WEB-INF/pages/pswchange.jsp";


    @EJB
    private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher(LOGIN).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        List<Error> errors = new ArrayList<>();
        Error errorPassword = new Error();
        Error errorEmail = new Error();
        Error errorInactive = new Error();

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

            try {
                if (userDAO.isValid(email, password)) {

                    //Créer une session si credentials validés
                    HttpSession session = request.getSession();
                    User currentUser = userDAO.findByIdEmail(email);
                    session.setAttribute("user", currentUser);
                    request.setAttribute("isAdmin", currentUser.isAdmin());

                    // si le status est changedPassword redirect to change password
                    // TODO use enums for status
                    switch (currentUser.getState()) {
                        case 0:
                            errorInactive.setErrorText("Inactive account.<br> Contact an administrator!");
                            errorInactive.setError(true);
                            break;
                        case 1:
                            request.getRequestDispatcher(HOME).forward(request, response);
                            break;
                        case 2:
                            request.getRequestDispatcher(PSWCHANGE).forward(request, response);
                            break;
                    }
                } else {
                    errorPassword.setErrorText("wrong password");
                    errorPassword.setError(true);
                    // TODO correct message, should be more explicit if user not exits -> bad email or wrong password
                    errorEmail.setErrorText("email not found");
                    errorEmail.setError(true);
                }
            }
            catch (Exception e){
                request.setAttribute("error","There was a problem when test if the login informations were valid or when we get the user in the database");
                request.setAttribute("errorContent",e.getMessage());
                request.getRequestDispatcher(ErrorServlet.ERROR).forward(request, response);
            }

        }

        errors.add(errorEmail);
        errors.add(errorPassword);
        errors.add(errorInactive);

        request.setAttribute("errors", errors);
        request.getRequestDispatcher(LOGIN).forward(request, response);
    }
}
