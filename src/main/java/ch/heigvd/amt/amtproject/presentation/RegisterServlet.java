package ch.heigvd.amt.amtproject.presentation;

import ch.heigvd.amt.amtproject.model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;


@WebServlet("/register")
public class RegisterServlet extends javax.servlet.http.HttpServlet {

    public static String VUE = "/WEB-INF/pages/register.jsp";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VUE).forward(request, response);
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String SecondPassword = request.getParameter("secondPassword");
        User user = new User(name,lastname,password,email,true, 0);

        boolean login = true;

        /*
        if (!email.contains("@")) {
            errorEmail.setErrorText( "bad email structure !");
            errorEmail.setError(true);
            login = false;
        }
        else {
            errorEmail.setValue(email);
        }

        if (name.equals("")){
            errorName.setErrorText("name empty !");
            errorName.setError(true);
            login = false;
        }
        else {
            errorName.setValue(name);
        }

        if (lastname.isEmpty()){
            errorLastName.setErrorText("lastName empty !");
            errorLastName.setError(true);
            login = false;
        }
        else {
            errorLastName.setValue(lastname);
        }
        */

        if (login) {
            //User user = new User(name, lastname, email);
            //users.add(user);
            doGet(request, response);
            return;
        }


        //request.setAttribute("value", value);
        request.getRequestDispatcher(VUE).forward(request, response);
    }
}
