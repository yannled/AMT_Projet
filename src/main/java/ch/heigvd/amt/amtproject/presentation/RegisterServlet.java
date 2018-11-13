package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.business.DAO.UserDAOLocal;
import ch.heigvd.amt.amtproject.business.PasswordUtils;
import ch.heigvd.amt.amtproject.model.Error;
import ch.heigvd.amt.amtproject.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@WebServlet("/register")
public class RegisterServlet extends javax.servlet.http.HttpServlet {

    public String REGISTER = "/WEB-INF/pages/register.jsp";
    public String LOGIN = "/WEB-INF/pages/login.jsp";


    @EJB
    private UserDAOLocal userDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(REGISTER).forward(request, response);
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

        try {
            if (userDAO.isExist(email)) {
                errorEmail.setErrorText("email already exists");
                errorEmail.setError(true);
                syntaxOK = false;
            } else {
                errorEmail.setValue(email);
            }
        }
        catch (Exception e){
            response.getWriter().println("There was a problem when we test if this email already exist");
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
            errorPassword.setValue(password);
        }

        if (secondPassword.isEmpty()){
            errorSecondPassword.setErrorText("password empty !");
            errorSecondPassword.setError(true);
            syntaxOK = false;
        }
        else {
            errorSecondPassword.setValue(secondPassword);
        }

        // we set a default avatar to the user database for a new registered user
        String defaultAvatarPath = getServletContext().getRealPath("images/defaultAvatar.png");
        InputStream inputStream = new DataInputStream(new FileInputStream(new File(defaultAvatarPath)));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        byte[] imageBytes = outputStream.toByteArray();
        String base64Avatar = Base64.getEncoder().encodeToString(imageBytes);


        inputStream.close();
        outputStream.close();

        if (syntaxOK) {
            try {
                // TODO il pourrait etre judicieux de faire le hash du mot de passe encore plus tot dans le programme
                User user = new User(name, lastname, PasswordUtils.generatePasswordHash(password), email, false, 1, base64Avatar);
                userDAO.create(user);

            }catch (NoSuchAlgorithmException | InvalidKeySpecException | RuntimeException e){
                // TODO: make a better error handle
                System.out.print(e.getMessage());
                response.getWriter().println("There was a problem when we create this user or when we generate the hash of this password.");
            }


            request.getRequestDispatcher(LOGIN).forward(request, response);
            return;
        }

        errors.add(errorName);
        errors.add(errorLastName);
        errors.add(errorEmail);
        errors.add(errorPassword);
        errors.add(errorSecondPassword);

        request.setAttribute("errors", errors);
        request.getRequestDispatcher(REGISTER).forward(request, response);
    }
}
