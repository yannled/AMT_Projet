package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;
import ch.heigvd.amt.amtproject.business.PasswordUtils;
import ch.heigvd.amt.amtproject.model.Error;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/register")
public class RegisterServlet extends javax.servlet.http.HttpServlet {

    public String VUE = "/WEB-INF/pages/register.jsp";

    @EJB
    private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("first-name");
        String lastname = request.getParameter("last-name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass1");
        String secondPassword = request.getParameter("pass2");

        List<Error> errors = new ArrayList<>();
        Error errorName = new Error();
        Error errorLastName = new Error();
        Error errorEmail = new Error();
        Error errorPassword = new Error();
        Error errorSecondPassword = new Error();

        boolean syntaxOK = true;

        if (!email.contains("@")) {
            errorEmail.setErrorText( "bad email structure !");
            errorEmail.setError(true);
            syntaxOK = false;
        }
        else {
            errorEmail.setValue(email);
        }

        if (name.equals("")){
            errorName.setErrorText("name empty !");
            errorName.setError(true);
            syntaxOK = false;
        }
        else {
            errorName.setValue(name);
        }

        if (lastname.isEmpty()){
            errorLastName.setErrorText("lastName empty !");
            errorLastName.setError(true);
            syntaxOK = false;
        }
        else {
            errorLastName.setValue(lastname);
        }

        if(!password.equals(secondPassword)){
            errorSecondPassword.setErrorText("not equal to password");
            errorSecondPassword.setError(true);
            syntaxOK = false;
        }

        if (password.isEmpty()){
            errorPassword.setErrorText("password empty !");
            errorPassword.setError(true);
            syntaxOK = false;
        }
        else {
            errorLastName.setValue(lastname);
        }

        if (secondPassword.isEmpty()){
            errorSecondPassword.setErrorText("password empty !");
            errorSecondPassword.setError(true);
            syntaxOK = false;
        }
        else {
            errorLastName.setValue(lastname);
        }

        //TODO : utiliser la fonction si dessous.
        /*if(userDAO.isExist(email)){
            errorEmail.setErrorText( "email already use !");
            errorEmail.setError(true);
            syntaxOK = false;
        }*/

        if (syntaxOK) {
            try {
                User user = new User(name, lastname, PasswordUtils.generatePasswordHash(password), email, false, 1);
                userDAO.create(user);
            }catch (NoSuchAlgorithmException | InvalidKeySpecException e){
                // TODO: make a better error handle
                System.out.print(e.getMessage());
            }

            // TODO: redirect to login page, is not working
            //request.getRequestDispatcher("login");
            return;
        }

        errors.add(errorName);
        errors.add(errorLastName);
        errors.add(errorEmail);
        errors.add(errorPassword);
        errors.add(errorSecondPassword);

        request.setAttribute("errors", errors);
        request.getRequestDispatcher(VUE).forward(request, response);
    }
}
